/*
 * utf-8
 */
package br.gov.finep.ToComFome.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;

import br.com.caelum.stella.type.Estado;
import br.gov.finep.ToComFome.modelo.Empresa;
import br.gov.finep.ToComFome.servico.ServicoEmpresa;
import br.gov.finep.safeparams.SafeParams;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TelaBuscaController extends SelectorComposer<Vlayout> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Wire
    private Vbox conteudo;

    @Wire
    private Button btnVisualizar;

    @Wire
    private Grid gridResultado;


    @Override
    public void doBeforeComposeChildren(Vlayout comp) throws Exception {
        // É executado antes do init()
    }

    @Override
    public void doAfterCompose(Vlayout root) throws Exception {
        // Equivalente ao init()
        super.doAfterCompose(root);

        // Filtro
        Groupbox groupbox = criarFiltro();

        // Grid Resultado
        this.gridResultado = criarGridResultado();

        // Conteudo
        this.conteudo.appendChild(groupbox);
        this.conteudo.appendChild(new Space());
        this.conteudo.appendChild(this.gridResultado);
    }

    private Groupbox criarFiltro() {
        Groupbox groupbox = new Groupbox();
        groupbox.setStyle("max-width:500px");
        
        Grid gridFiltro = new Grid();
        gridFiltro.setStyle("border:0; max-width:500px");
        gridFiltro.setOddRowSclass(null);
        Columns columns = new Columns();
        Column column1 = new Column();
        column1.setHflex("1");
        column1.setAlign("center");
        Column column2 = new Column();
        column2.setHflex("1");
        

        columns.appendChild(column1);
        columns.appendChild(column2);

        gridFiltro.appendChild(columns);

        Rows rows = new Rows();
        Row row = new Row();

        Textbox textboxComida = new Textbox();
        
        row.appendChild(textboxComida);

        this.btnVisualizar = new Button("Buscar");
        this.btnVisualizar.setId("btnBuscar");
        this.btnVisualizar.setIconSclass("z-icon-search");
        this.btnVisualizar.setSclass("btn-primary btn-sm");
        this.btnVisualizar.setMold("bs");
        row.appendChild(this.btnVisualizar);
        rows.appendChild(row);
        gridFiltro.appendChild(rows);
        groupbox.appendChild(gridFiltro);
        return groupbox;
    }

    private Grid criarGridResultado() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Grid gridResultado = new Grid();
        
        Columns columns = new Columns();

        Column column1 = new Column(" ");
        column1.setAlign("center");
        columns.appendChild(column1);

        Column column2 = new Column(" ");
        column2.setAlign("center");
        columns.appendChild(column2);

        Column column3 = new Column(" ");
        column3.setAlign("center");
        columns.appendChild(column3);

        gridResultado.appendChild(columns);

        gridResultado.setRowRenderer(new RowRenderer<Empresa>() {
            @Override
            public void render(Row row, final Empresa data, int index) throws Exception {
                row.appendChild(new Label("AA"));
                row.appendChild(new Label("BB"));
                row.appendChild(new Label("CC"));
            }
        });

        return gridResultado;
    }

    public Vbox getConteudo() {
        return conteudo;
    }

    public void setConteudo(Vbox conteudo) {
        this.conteudo = conteudo;
    }

    public Grid getGridResultado() {
        return gridResultado;
    }

    public void setGridResultado(Grid gridResultado) {
        this.gridResultado = gridResultado;
    }
}
