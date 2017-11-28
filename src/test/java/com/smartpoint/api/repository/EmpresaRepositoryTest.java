package com.smartpoint.api.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.smartpoint.api.entities.Empresa;
import com.smartpoint.api.entities.Funcionario;
import com.smartpoint.api.enums.PerfilEnum;
import com.smartpoint.api.repositories.EmpresaRepository;
import com.smartpoint.api.repositories.FuncionarioRepository;
import com.smartpoint.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private static final String CNPJ = "51463645000100";
	
	private static final String EMAIL = "email@email.com";
	private static final String CPF = "24291173474";

	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpj(CNPJ);
		this.empresaRepository.save(empresa);
		//this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}
	
	
//	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
//		Funcionario funcionario = new Funcionario();
//		funcionario.setNome("Fulano de Tal");
//		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
//		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
//		funcionario.setCpf(CPF);
//		funcionario.setEmail(EMAIL);
//		funcionario.setEmpresa(empresa);
//		return funcionario;
//	}
	
	@After
    public final void tearDown() { 
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarPorCnpj() {
		Empresa empresa = this.empresaRepository.findByCnpj(CNPJ);
		assertEquals(CNPJ, empresa.getCnpj());
	}
	
	//Testando lazy load
//	@Test
//	public void testeBuscarFuncionariosDaEmpresa() {
//		Empresa empresa = this.empresaRepository.findById(1l);
//		assertNotNull(empresa);
//		assertEquals(1, empresa.getFuncionarios().size());
//	 }

}
