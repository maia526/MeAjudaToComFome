/*
 * utf-8
 */
package br.gov.finep.ToComFome.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.finep.ToComFome.modelo.Business;
import br.gov.finep.ToComFome.modelo.BusinessList;
import br.gov.finep.safeparams.SafeParams;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TelaBuscaController extends SelectorComposer<Vlayout> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Wire
    private Vbox conteudo;

    @Wire
    private Button btnVisualizar;

    @Wire
    private Textbox textboxComida;
    
    @Wire
    private Grid gridResultado; 
    
    private String termoPesquisaAnterior = "";
    
    private List<Business> businessesFiltrado = new ArrayList<>();
    
    private ListModelList<List<Business>> listaDeResultadoPorRow = new ListModelList<>();

    @Override
    public void doBeforeComposeChildren(Vlayout comp) throws Exception {
        // É executado antes do init()
    }

    @Override
    public void doAfterCompose(Vlayout root) throws Exception {
        // Equivalente ao init()
        super.doAfterCompose(root);
        
        this.businessesFiltrado.clear();
        this.listaDeResultadoPorRow.clear();
        
        this.conteudo.setAlign("center");
        
        // Filtro
        Groupbox groupbox = criarFiltro();

        // Grid Resultado
        this.gridResultado = criarGridResultado(this.listaDeResultadoPorRow);
        
        // Conteudo
        this.conteudo.appendChild(groupbox);
        this.conteudo.appendChild(new Space());
        this.conteudo.appendChild(this.gridResultado);
    }

    private Groupbox criarFiltro() {
        Groupbox groupbox = new Groupbox();
        groupbox.setStyle("max-width:350px");
        groupbox.appendChild(new Caption("Busca"));
        
        Grid gridFiltro = new Grid();
        gridFiltro.setStyle("border:0; max-width:500px");
        gridFiltro.setOddRowSclass(null);
        Columns columns = new Columns();
        Column column1 = new Column();
        column1.setHflex("2");
        column1.setAlign("center");
        Column column2 = new Column();
        column2.setHflex("1");
        column2.setAlign("start");
    
        Rows rows = new Rows();
        Row row = new Row();

        this.textboxComida = new Textbox();
       
        this.btnVisualizar = new Button("Buscar");
        this.btnVisualizar.setId("btnBuscar");
        this.btnVisualizar.setIconSclass("z-icon-search");
        this.btnVisualizar.setSclass("btn-primary btn-sm");
        this.btnVisualizar.setMold("bs");
        
        
        columns.appendChild(column1);
        columns.appendChild(column2);
        row.appendChild(this.textboxComida);
        row.appendChild(this.btnVisualizar);
        rows.appendChild(row);
        gridFiltro.appendChild(columns);
        gridFiltro.appendChild(rows);
        groupbox.appendChild(gridFiltro);
        
        return groupbox;
    }
    
    @Listen("onClick = #btnBuscar")
    @NotifyChange({"gridResultado", "gridResultadoModel", "businessesFiltrado"})
	public void filtrarPorTermo() throws IOException {
    			if (termoPesquisaAnterior.equals(textboxComida.getValue()))
    				return;
	           	OkHttpClient client = new OkHttpClient();
	           
	           	String url = "https://api.yelp.com/v3/businesses/search?location=Rio%20de%20Janeiro&term=" + textboxComida.getValue();
	
	           	Request request = new Request.Builder()
	           	   .url(url)
	           	   .get()
	           	   .addHeader("accept", "application/json")
	           	   .addHeader("Authorization", "Bearer uQGmhzoMFpY91wBiQTGdD2r9m5Ut2gx8Bh6yKEEy5mzWtnSS9ftbcaWG-JGSrYS6QCdXiSd636JC6qgUH-eFg_OhY4UlifCmSnsU_k4mTDejAYSGlfp5Bal59o2xZHYx")
	           	   .build();
	           	Response response = client.newCall(request).execute();
	           	String jsonString = response.body().string();
	           	ObjectMapper objectMapper = new ObjectMapper();      	
	           	BusinessList businessList = objectMapper.readValue(jsonString, BusinessList.class);
	           	
	           	businessesFiltrado.clear();
	           	businessesFiltrado.addAll(businessList.getBusinesses());
	           	
	           	listaDeResultadoPorRow.clear();
	           	listaDeResultadoPorRow = gerarModelDoGridResultado();
	           	
	           	
	           	conteudo.removeChild(gridResultado);
	           	gridResultado = criarGridResultado(listaDeResultadoPorRow);
	           	conteudo.appendChild(gridResultado);

	           	termoPesquisaAnterior = textboxComida.getValue();

	}
   
	private ListModelList<List<Business>> gerarModelDoGridResultado() {
		ListModelList<List<Business>> model = new ListModelList<>();
		
		for (int i = 0; i < this.businessesFiltrado.size(); i = i + 3) {
			List<Business> row = new ArrayList<>();
			
			for (int j = i; j < i + 3 && j < this.businessesFiltrado.size() ; j++) {
				row.add(this.businessesFiltrado.get(j));
			}
			model.add(row);
		}
		
		
		return model;
	}

    private Grid criarGridResultado(ListModelList<List<Business>> listaResultado) {
        Grid grid = new Grid();
        //grid.setId("resultadoListBox");
        grid.setModel(listaResultado);
        grid.setId("gridResultado");
        Columns columns = new Columns();
        
        Column coluna1 = new Column();
        Column coluna2 = new Column();
        Column coluna3 = new Column();
        
        columns.appendChild(coluna1);
        columns.appendChild(coluna2);
        columns.appendChild(coluna3);
        
        grid.appendChild(columns);
        
        grid.setRowRenderer(new RowRenderer<List<Business>>() {
			@Override
			public void render(Row row, List<Business> data, int index) throws Exception {
				for (int i = 0; i < data.size(); i++) {
					Map<String, Object> mapaBusiness = gerarMapaBusiness(data.get(i));
					Label label = new Label(data.get(i).getName());
					label.addEventListener(Events.ON_CLICK, redirecionarParaTelaRestaurante(mapaBusiness));
					//TODO: botar foto e outras informações
					row.appendChild(label);
				}
					
			}
			
			public String gerarStringDisplayAdress(Business business) {
				List<String> partesEndereco = business.getLocation().getDisplay_address();
				return partesEndereco.get(0);
			}
			
			public Map<String, Object> gerarMapaBusiness(Business business){
				Map<String, Object> mapaBusiness = new HashMap();
				String endereco = gerarStringDisplayAdress(business);
				mapaBusiness.put("endereco", endereco);
				mapaBusiness.put("id", business.getId());
				mapaBusiness.put("alias", business.getAlias());
				mapaBusiness.put("name", business.getName());
				mapaBusiness.put("image_url", business.getImage_url());
				mapaBusiness.put("url", business.getUrl());
				mapaBusiness.put("review_count", business.getReview_count());
				mapaBusiness.put("categories", business.getCategories());
				mapaBusiness.put("rating", business.getRating());
				mapaBusiness.put("coordinates", business.getCoordinates());
				mapaBusiness.put("price", business.getPrice());
				mapaBusiness.put("location", business.getLocation());
				mapaBusiness.put("phone", business.getPhone());
				mapaBusiness.put("display_phone", business.getDisplay_phone());
				mapaBusiness.put("distance", business.getDistance());
				return mapaBusiness;
			}

			private EventListener redirecionarParaTelaRestaurante(Map<String, Object> mapaBusiness) {
				return new EventListener() {

					@Override
					public void onEvent(Event event) throws Exception {
						Executions.getCurrent().sendRedirect(MenuVM.urlTelaRestaurante() + "?" + new SafeParams("mapaBusiness", mapaBusiness));
						
					}
					
				};
			}		
        });
        
        
        return grid;
    }
    
    public Vbox getConteudo() {
        return conteudo;
    }

    public void setConteudo(Vbox conteudo) {
        this.conteudo = conteudo;
    }

	public Button getBtnVisualizar() {
		return btnVisualizar;
	}

	public void setBtnVisualizar(Button btnVisualizar) {
		this.btnVisualizar = btnVisualizar;
	}

	public Grid getGridResultado() {
		return gridResultado;
	}

	public void setGridResultado(Grid gridResultado) {
		this.gridResultado = gridResultado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Textbox getTextboxComida() {
		return textboxComida;
	}

	public void setTextboxComida(Textbox textboxComida) {
		this.textboxComida = textboxComida;
	}

	public List<Business> getBusinessesFiltrado() {
		return businessesFiltrado;
	}

	public void setBusinessesFiltrado(List<Business> businessesFiltrado) {
		this.businessesFiltrado = businessesFiltrado;
	}

	public void setGridResultadoModel(ListModelList<List<Business>> gridResultadoModel) {
		this.listaDeResultadoPorRow = gridResultadoModel;
	}

	public ListModelList<List<Business>> getListaDeResultadoPorRow() {
		return listaDeResultadoPorRow;
	}

	public void setListaDeResultadoPorRow(ListModelList<List<Business>> listaDeResultadoPorRow) {
		this.listaDeResultadoPorRow = listaDeResultadoPorRow;
	}   
	
}
