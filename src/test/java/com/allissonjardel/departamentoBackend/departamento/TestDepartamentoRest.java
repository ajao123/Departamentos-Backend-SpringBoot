package com.allissonjardel.departamentoBackend.departamento;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.allissonjardel.departamentoBackend.controller.DepartamentoController;
import com.allissonjardel.departamentoBackend.model.Departamento;
import com.allissonjardel.departamentoBackend.model.dto.DepartamentoDTO;
import com.allissonjardel.departamentoBackend.repository.DepartamentoRepository;
import com.allissonjardel.departamentoBackend.util.Erro;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestDepartamentoRest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	DepartamentoRepository repository;
	
	Departamento entity;
	
	@Before
	public void start() {
		entity = new Departamento(null, "teste", "Departamento de Testes");
		repository.save(entity);
	}

	@After
	public void end() {
		repository.deleteAll();
	}
	
	// Inserindo Departamentos
	
	@Test
	public void inserirDepartamento() {
		
		Departamento departamento =  new Departamento(null, "Alimentos", "Departamento de alimentos");
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(departamento, null);
		ResponseEntity<DepartamentoDTO> resposta = 
				testRestTemplate.exchange("/departamento",HttpMethod.POST, httpEntity, DepartamentoDTO.class);
	
		Assert.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		Assert.assertEquals(departamento.getNome(), resposta.getBody().getName());
		Assert.assertEquals(departamento.getDescricao(), resposta.getBody().getDescricao());
		Assert.assertEquals(2, repository.findAll().size());
		
		repository.deleteAll();
	}
	
	@Test
	public void inserirDepartamentoComNomeNulo() {
		
		Departamento departamento =  new Departamento(null, null, "Departamento de alimentos");
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(departamento);
		ResponseEntity<List<Erro>> resposta = 
				testRestTemplate.exchange("/departamento",HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<Erro>>() {});
		
//		System.out.println("\n Resposta Status Code: " + resposta.getStatusCodeValue() + "\n");
//		System.out.println("\n Resposta: " + resposta.getBody().get(0).getMessageUser() + "\n");
		
		Assert.assertTrue(resposta.getBody().toString().contains("Nome é obrigatório(a)"));
		
	}
	
	@Test
	public void inserirDepartamentoComDescricaoNula() {
		
		Departamento departamento =  new Departamento(null, "Alimentos", null);
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(departamento, null);
		ResponseEntity<List<Erro>> resposta = 
				testRestTemplate.exchange("/departamento",HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<Erro>>() {});
		
//		System.out.println("\n Resposta Status Code: " + resposta.getStatusCodeValue() + "\n");
//		System.out.println("\n Resposta: " + resposta.getBody().get(0).getMessageUser() + "\n");
		
		Assert.assertTrue(resposta.getBody().toString().contains("Descrição é obrigatório(a)"));
		
	}
	
	@Test
	public void inserirDepartamentoComAtributosNulos() {
		
		Departamento departamento =  new Departamento(null, null, null);
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(departamento, null);
		ResponseEntity<List<Erro>> resposta = 
				testRestTemplate.exchange("/departamento",HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<Erro>>() {});
		
		Assert.assertTrue(resposta.getBody().toString().contains("Nome é obrigatório(a)"));
		Assert.assertTrue(resposta.getBody().toString().contains("Descrição é obrigatório(a)"));
		//
	}
	
	@Test
	public void inserirDepartamentoComNomeInvalido() {
		
		Departamento departamento =  new Departamento(null, "", "Departamento de Alimentos");
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(departamento, null);
		ResponseEntity<List<Erro>> resposta = 
				testRestTemplate.exchange("/departamento",HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<Erro>>() {});
		
		Assert.assertTrue(resposta.getBody().toString().contains("Nome deve ter o tamanho entre 3 e 22"));
	}

	@Test
	public void inserirDepartamentoComNomeExistente() {
		
		repository.save(new Departamento(null, "Alimentos", "Departamento de alimentos"));
		Departamento departamento =  new Departamento(null, "Alimentos", "Departamento de Alimentos");
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(departamento, null);
		ResponseEntity<List<Erro>> resposta = 
				testRestTemplate.exchange("/departamento",HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<Erro>>() {});
	
		//System.out.println("\n \n Resposta: " + resposta.getBody().get(0).getMessageUser());
		
		// Com validator
		Assert.assertTrue(resposta.getBody().toString().contains("Esse nome ja pertence a um departamento"));
		
		//  Usando Integrity Exception
		//Assert.assertTrue(resposta.getBody().toString().contains("Integridade dos dados nao foi mantida"));
	}

	// Buscando Departamentos
	
	@Test
	public void getDepartamentosUsandoString() {
		ResponseEntity<String> resposta =
				testRestTemplate.exchange("/departamento",HttpMethod.GET,null, String.class);

		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());		
		String result = "[{\"id\":"+entity.getId()+",\"name\":\"teste\",\"descricao\":\"Departamento de Testes\""
				+ ",\"pesquisadores\":[],\"secretarios\":[],\"funcionariosLimpeza\":[]"
				+ ",\"projetos\":[]}]";
		
		Assert.assertEquals(result, resposta.getBody());

	}
	
	@Test
	public void deveMostrarTodosDepartamentosUsandoList() {
		ParameterizedTypeReference<List<DepartamentoDTO>> tipoRetorno =
				new ParameterizedTypeReference<List<DepartamentoDTO>>() {};

		ResponseEntity<List<DepartamentoDTO>> resposta =
				testRestTemplate.exchange("/departamento",HttpMethod.GET,null, tipoRetorno);
		
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Assert.assertEquals(1, resposta.getBody().size());		
	}

	@Test
	public void deveMostrarUmDepartamento() {
		ResponseEntity<DepartamentoDTO> resposta =
				testRestTemplate.exchange("/departamento/{id}",HttpMethod.GET,null
						, DepartamentoDTO.class, entity.getId() );
		
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Assert.assertEquals(new DepartamentoDTO(entity).toString(), resposta.getBody().toString());
	}
	
	@Test
	public void buscaUmDepartamentoDeveRetornarNaoEncontrado() {

		ResponseEntity<DepartamentoDTO> resposta =
				testRestTemplate.exchange("/departamento/{id}", HttpMethod.GET, null, DepartamentoDTO.class, 100L);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
		Assert.assertNull(resposta.getBody());
	}
	
	// Deletando Departamentos
	
	@Test
	public void removerDeveExcluirDepartamento() {
		ResponseEntity<?> resposta = 
				testRestTemplate.exchange("/departamento/{id}",HttpMethod.DELETE,null
						, DepartamentoDTO.class, entity.getId());
		
		Assert.assertEquals(HttpStatus.NO_CONTENT,resposta.getStatusCode());
		Assert.assertNull(resposta.getBody());
	}

	@Test
	public void removerDeveExcluirDepartamentoComDelete() {
		testRestTemplate.delete("/departamento/"+entity.getId());
		
		Optional<Departamento> resultado = repository.findById(entity.getId());
		Assert.assertEquals(Optional.empty(), resultado);
	}
	
	@Test
	public void removerDepartamentoInexistente() {
		ResponseEntity<List<Erro>> resposta = 
				testRestTemplate.exchange("/departamento/100",HttpMethod.DELETE,null
						, new ParameterizedTypeReference<List<Erro>>() {});
		
		Assert.assertTrue(resposta.toString().contains("Recurso não encontrado"));
	}
	
	//Atualizando Departamentos
	
	@Test
	public void alterarDeveRetornarMensagemDeErro() {
		entity.setNome(null);
		entity.setDescricao(null);
		
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(entity);
		
		ResponseEntity<List<Erro>> resposta = 
				testRestTemplate.exchange("/departamento", HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<List<Erro>>() {});
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		Assert.assertTrue(resposta.getBody().toString().contains("Nome é obrigatório(a)"));
		Assert.assertTrue(resposta.getBody().toString().contains("Descrição é obrigatório(a)"));
	}

	@Test
	public void alterarDepartamento() {
		entity.setNome("Novo Departamento");
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(entity);
		ResponseEntity<DepartamentoDTO> resposta = 
				testRestTemplate.exchange("/departamento", HttpMethod.PUT, httpEntity, DepartamentoDTO.class);
		
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
		Assert.assertEquals(resposta.getBody().getId(), entity.getId());
		Assert.assertEquals(resposta.getBody().getName(), entity.getNome());
		Assert.assertEquals(resposta.getBody().getDescricao(), entity.getDescricao());
		
	}
	
	@Test
	public void alterarDeveAlterarDepartamento() throws Exception {
		
		entity.setNome("Teste 99");
		HttpEntity<Departamento> httpEntity = new HttpEntity<>(entity);
		ResponseEntity<DepartamentoDTO> respostaAtualizar = 
				testRestTemplate.exchange("/departamento", HttpMethod.PUT, httpEntity, DepartamentoDTO.class);
	
	
		ResponseEntity<DepartamentoDTO> respostaBuscar =
				testRestTemplate.exchange("/departamento/{id}",HttpMethod.GET,null
						, DepartamentoDTO.class, entity.getId());
		
		Assert.assertEquals("Teste 99", respostaBuscar.getBody().getName());
		Assert.assertEquals("Teste 99", respostaAtualizar.getBody().getName());
		Assert.assertEquals(respostaBuscar.getBody().getName(), respostaAtualizar.getBody().getName());
	}
	
}
