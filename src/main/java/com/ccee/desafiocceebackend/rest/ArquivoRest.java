package com.ccee.desafiocceebackend.rest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ccee.desafiocceebackend.entity.Arquivo;
import com.ccee.desafiocceebackend.service.ArquivoService;

@RestController
@RequestMapping("/api/arquivo")
public class ArquivoRest {

	private final ArquivoService arquivoService;

	@Autowired
	public ArquivoRest(ArquivoService arquivoService) {
		this.arquivoService = arquivoService;
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("arquivo") MultipartFile file) {

		if (file.isEmpty()) {
			return "Falha no envio do arquivo.";
		}
		try {
			List<Arquivo> arquivos = new ArrayList<>();

			String arquivoXml = new String(file.getBytes(), StandardCharsets.UTF_8);

			arquivoXml = arquivoService.removerDadosConfidenciais(arquivoXml);

			List<String> codigosAgente = arquivoService.extrairCodigosAgente(arquivoXml);
			codigosAgente.stream().forEach(codigoAgente -> {
				System.out.println("Código do Agente: " + codigoAgente);
			});

			List<String> datas = arquivoService.extrairDatasAgente(arquivoXml);
			List<String> regioes = arquivoService.extrairRegioesAgente(arquivoXml);

			if (codigosAgente.size() == datas.size() && datas.size() == regioes.size()) {
				for (int i = 0; i < codigosAgente.size(); i++) {
					Arquivo arquivo = new Arquivo();
					arquivo.setCodigoAgente(codigosAgente.get(i));
					arquivo.setData(datas.get(i));
					arquivo.setRegiao(regioes.get(i));

					arquivos.add(arquivo);
				}
			}

			arquivos.stream().forEach(arquivo -> {
				//arquivoService.salvarArquivo(arquivo);
			});

			return "Arquivo recebido e processado com sucesso.";
		} catch (Exception e) {
			return "Erro ao processar o arquivo: " + e.getMessage();
		}
	}

	@GetMapping("/consolidado")
	public Map<String, List<Arquivo>> getDadosPorRegiao() {

		List<Arquivo> dados = new ArrayList<>();
		try {
			dados = arquivoService.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, List<Arquivo>> dadosConsolRegiao = new HashMap<>();
		dados.stream().forEach(dado -> {
			String regiao = dado.getRegiao();
			if (!dadosConsolRegiao.containsKey(regiao)) {
				dadosConsolRegiao.put(regiao, new ArrayList<>());
			}
			dadosConsolRegiao.get(regiao).add(dado);
		});

		return dadosConsolRegiao;
	}
}
