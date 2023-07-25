/*
 * utf-8
 */
package br.gov.finep.ToComFome.web;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.finep.Ambiente;
import br.gov.finep.ToComFome.modelo.Leg;
import br.gov.finep.ToComFome.modelo.Location;
import br.gov.finep.ToComFome.modelo.Maneuver;
import br.gov.finep.ToComFome.modelo.RequestCaminho;
import br.gov.finep.ToComFome.modelo.Route;
import br.gov.finep.ToComFome.modelo.YelpData;
import br.gov.finep.safeparams.SafeParams;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TelaRestauranteController extends SelectorComposer<Vlayout> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Wire
    private Vbox conteudo;

    @Wire
    private Button btnVisualizar;

    @Wire
    private Grid gridResultado;

    private Map<String, Object> business;
    
    private YelpData reviews;
    @Override
    public void doBeforeComposeChildren(Vlayout comp) throws Exception {
        // É executado antes do init()
    }

    @Override
    public void doAfterCompose(Vlayout root) throws Exception {
        // Equivalente ao init()
        super.doAfterCompose(root);
        
        String parametros = Executions.getCurrent().getParameter(SafeParams.DEFAULT_DATA_PARAM);
    	if (parametros != null) {
    		business = (Map<String, Object>) new SafeParams(parametros).get("mapaBusiness");
    		this.reviews = pegarAvaliacoes((String)business.get("id"));
        }
        
        Label nomeRestauranteLabel = new Label((String)business.get("name"));
        nomeRestauranteLabel.setStyle("font-size: 30px;");
        this.conteudo.appendChild(nomeRestauranteLabel);
        
        Groupbox perfil = criaGroupboxPerfil();
        this.conteudo.appendChild(perfil);
    }
    
    public String encodeStringParaUTF8(String s) {    	
    	byte[] bytes = s.getBytes();
    	
    	String sFinal = new String(bytes, StandardCharsets.US_ASCII);
    	sFinal = sFinal.replace(" ", "+");
    	sFinal = sFinal.replace(",", "%2C");
    	
    	return sFinal;
    }
    
    public RequestCaminho pegarCaminho(String from, String to) throws IOException {
    	OkHttpClient client = new OkHttpClient();
    	Request request = new Request.Builder()
    	  .url("https://www.mapquestapi.com/directions/v2/route?key=duN2Qihddlk30fSuxo4cBqJ8XG3y6Dr3&from=" + 
    			  from + "&to=" + to + 
    			  "&outFormat=json&ambiguities=ignore&routeType=fastest&doReverseGeocode=false&enhancedNarrative=false&avoidTimedConditions=false")
    	  .get()
    	  .addHeader("accept", "application/json")
    	  .addHeader("Authorization", "Bearer uQGmhzoMFpY91wBiQTGdD2r9m5Ut2gx8Bh6yKEEy5mzWtnSS9ftbcaWG-JGSrYS6QCdXiSd636JC6qgUH-eFg_OhY4UlifCmSnsU_k4mTDejAYSGlfp5Bal59o2xZHYx")
    	  .build();

    	Response response = client.newCall(request).execute();
       	String jsonString = response.body().string();
       	ObjectMapper objectMapper = new ObjectMapper();      	
       	
       	return objectMapper.readValue(jsonString, RequestCaminho.class);

    }
    
    
    public YelpData pegarAvaliacoes(String id) throws IOException {
    	OkHttpClient client = new OkHttpClient();

    	Request request = new Request.Builder()
    	  .url("https://api.yelp.com/v3/businesses/" + id +"/reviews")
    	  .get()
    	  .addHeader("accept", "application/json")
    	  .addHeader("Authorization", "Bearer uQGmhzoMFpY91wBiQTGdD2r9m5Ut2gx8Bh6yKEEy5mzWtnSS9ftbcaWG-JGSrYS6QCdXiSd636JC6qgUH-eFg_OhY4UlifCmSnsU_k4mTDejAYSGlfp5Bal59o2xZHYx")
    	  .build();

    	Response response = client.newCall(request).execute();
       	String jsonString = response.body().string();
       	ObjectMapper objectMapper = new ObjectMapper();      	
       	return objectMapper.readValue(jsonString, YelpData.class);
    }
    
    public String tratarString(String s) {
    	byte[] sBytes = s.getBytes(StandardCharsets.UTF_8);
    	return new String(sBytes, StandardCharsets.UTF_8);
    }
    
    public Image gerarImagemNota() {
    	Image imagemNota = new Image();
    	imagemNota.setId("imagemNota");
    	String url = retornarUrlImagemNota();
    	imagemNota.setSrc(url);
    	
    	return imagemNota;
    }
    
    public Image gerarImagemRestaurante() {
    	Image imagemRestaurante = new Image();
    	imagemRestaurante.setId("imagemRestaurante");
    	imagemRestaurante.setSrc((String) business.get("image_url"));
    	imagemRestaurante.setHeight("200px");
    	imagemRestaurante.setStyle("margin-left: 100px;");
    	
    	return imagemRestaurante;
    }
    
    public Hbox gerarHboxInformacoes() {
		Hbox informacoes = new Hbox();
		    	
    	Vbox vboxInformacoes = new Vbox();
    	vboxInformacoes.appendChild(new Label("Categorias: " + retornaStringCategorias((List<Map<String, Object>>)business.get("categories"))));
    	vboxInformacoes.appendChild(gerarImagemNota());	
    	vboxInformacoes.appendChild(new Label("Preço: " + business.get("price")));
    	vboxInformacoes.appendChild(new Label("Telefone: " + business.get("display_phone")));
 
    	informacoes.appendChild(vboxInformacoes);
    	
    	
    	Vbox vboxImagemRestaurante = new Vbox();
    	vboxImagemRestaurante.appendChild(gerarImagemRestaurante());
    	vboxImagemRestaurante.setAlign("end");
    	
    	
    	informacoes.appendChild(vboxImagemRestaurante);
    	
    	return informacoes;
    }
    
    public Groupbox criaGroupboxPerfil() throws IOException {
    	Groupbox groupbox = new Groupbox();
    	groupbox.setWidth("600px");
    	
    	Vbox vboxPerfil = new Vbox();
    	
    	Hbox informacoes = gerarHboxInformacoes();
  
    	Groupbox avaliacoesGroupbox = criaGroupboxAvaliacoes();
    	
    	Groupbox caminhoGroupbox = criaGroupboxCaminho();
    	
    	vboxPerfil.appendChild(informacoes);
    	vboxPerfil.appendChild(avaliacoesGroupbox);
    	vboxPerfil.appendChild(caminhoGroupbox);
    	
    	groupbox.appendChild(vboxPerfil);
    	return groupbox;
    }
    private static String getUrlAplicacao() {
        return Ambiente.uri("ToComFome").toTemplate();
    }
    
    public String retornarUrlImagemNota() {
    	String s = "";
    	double rating = (double)business.get("rating");
    	if (rating < 1)
    		s = getUrlAplicacao() + "/resources/imagem/small/small_0.png";
    	else if (rating >= 1 && rating < 1.5)
    		s = getUrlAplicacao() + "/resources/imagem/small/small_1.png";	
    	else if (rating >= 1.5 && rating < 2)
			s = getUrlAplicacao() + "/resources/imagem/small/small_1_half.png";
    	else if (rating >= 2 && rating < 2.5)	
			s = getUrlAplicacao() + "/resources/imagem/small/small_2.png";
    	else if (rating >= 2.5 && rating < 3)
			s = getUrlAplicacao() + "/resources/imagem/small/small_2_half.png";
    	else if (rating >= 3 && rating < 3.5)
			s = getUrlAplicacao() + "/resources/imagem/small/small_3.png";
    	else if (rating >= 3.5 && rating < 4)
			s = getUrlAplicacao() + "/resources/imagem/small/small_3_half.png";
    	else if (rating >= 4 && rating < 4.5)
			s = getUrlAplicacao() + "/resources/imagem/small/small_4.png";
    	else if (rating >= 4.5 && rating < 5)
			s = getUrlAplicacao() + "/resources/imagem/small/small_4_half.png";
    	else if (rating == 5) 
			s = getUrlAplicacao() + "/resources/imagem/small/small_5.png";
    	return s;
    }
    
    public String retornaStringCategorias(List<Map<String, Object>> mapaCategorias) {
    	String s = "";
    	
    	for (Map<String, Object> categoria : mapaCategorias) {
    		s += " " + categoria.get("title") + ",";
    	}
    	s = s.substring(0, s.length() -1);
    	
    	return s;
    }
    
    public Groupbox criaGroupboxCaminho() throws IOException {
    	Groupbox groupbox = new Groupbox();
    	groupbox.appendChild(new Caption("Como chegar?"));
    	groupbox.setWidth("100%");
    	
    	Hbox hbox = new Hbox();
    	Vbox vbox = new Vbox();
    	
    	String from = "Praia do Flamengo, 200";    	
    	String to = (String) business.get("endereco");
    	
    	RequestCaminho caminho = pegarCaminho(encodeStringParaUTF8(from), encodeStringParaUTF8(to));
    	
    	List<Leg> legs = caminho.getRoute().getLegs();
    	for (Leg leg : legs) {
    		List<Maneuver> maneuvers = leg.getManeuvers();
    		for (Maneuver maneuver : maneuvers) {
    			vbox.appendChild(new Label(maneuver.getNarrative()));
    		}
    	}
    	hbox.appendChild(vbox);
    	
    	String mapUrl = legs.get(0).getManeuvers().get(0).getMapUrl();
    	Image map = new Image();
    	map.setSrc(mapUrl);
    	hbox.appendChild(map);
    	
    	groupbox.appendChild(hbox);
    	
    	groupbox.setStyle("width:500px");
    	groupbox.setClosable(false);
    	return groupbox;
    	
    }
    
    public Groupbox criaGroupboxAvaliacoes() {
    	Groupbox groupbox = new Groupbox();
    	groupbox.appendChild(new Caption("Avaliação"));
    	groupbox.setWidth("100%");
    	
    	Vbox vbox = new Vbox();
    	Label nome = new Label(this.reviews.getReviews().get(0).getUser().getName());
    	nome.setStyle("font-weight: bold; margin-left: 5px");
    	vbox.appendChild(nome);
    	vbox.appendChild(new Label(this.reviews.getReviews().get(0).getText()));
    	
    	groupbox.appendChild(vbox);
    	
    	groupbox.setStyle("width:500px");
    	groupbox.setClosable(false);
    	return groupbox;
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
