package com.ccee.desafiocceebackend.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ccee.desafiocceebackend.entity.Arquivo;
import com.ccee.desafiocceebackend.service.ArquivoService;

@RestController
@RequestMapping("http://localhost:8080/api/arquivo")
public class ArquivoRest {
	
	private final ArquivoService arquivoService;

    @Autowired
    public ArquivoRest(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return "Falha no envio do arquivo.";
		}

		try {
			// Processar o arquivo XML e salvar os dados no banco de dados
			// Use bibliotecas Java para processar XML, extrair dados e salvar no banco de
			// dados

			// Exemplo: Imprimir o nome do arquivo
			System.out.println("Nome do arquivo: " + file.getOriginalFilename());

			// Exemplo: Imprimir o conteúdo do arquivo
			System.out.println("Conteúdo do arquivo: " + new String(file.getBytes()));

			// Exemplo: Processar o XML e salvar os dados no banco de dados
			// ...

			return "Arquivo recebido e processado com sucesso.";
		} catch (Exception e) {
			return "Erro ao processar o arquivo: " + e.getMessage();
		}
	}
	
	@GetMapping("/consolidado")
    public Map<String, List<Arquivo>> getDadosPorRegiao() {
        // Implemente a lógica de consulta e consolidação dos dados por região aqui
        // Use o repositório JPA para acessar os dados do banco de dados

        // Exemplo fictício de consulta e consolidação de dados por região:
        List<Arquivo> dados = new ArrayList<>();
		try {
			dados = arquivoService.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}

        Map<String, List<Arquivo>> dadosPorRegiao = dados.stream()
                .collect(Collectors.groupingBy(Arquivo::getRegiao));

        return dadosPorRegiao;
    }
}
