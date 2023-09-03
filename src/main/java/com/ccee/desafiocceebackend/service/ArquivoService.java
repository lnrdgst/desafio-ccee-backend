package com.ccee.desafiocceebackend.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ccee.desafiocceebackend.entity.Arquivo;
import com.ccee.desafiocceebackend.repository.ArquivoRepository;

@Component
public class ArquivoService {

	private final ArquivoRepository arquivoRepository;

	public ArquivoService(ArquivoRepository arquivoRepository) {
		this.arquivoRepository = arquivoRepository;
	}

	public List<Arquivo> buscarTodos() {
		return this.arquivoRepository.findAll();
	}

	public Arquivo salvarArquivo(Arquivo arquivo) {
		return this.arquivoRepository.save(arquivo);
	}

	public String removerDadosConfidenciais(String aquivoXml) {

		aquivoXml = aquivoXml.replaceAll("<precoMedio>.*?</precoMedio>", "<precoMedio></precoMedio>");

		return aquivoXml;
	}

	public List<String> extrairCodigosAgente(String arquivoXml) {
		List<String> codigosAgentes = new ArrayList<>();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(arquivoXml)));

			NodeList agentes = document.getElementsByTagName("agente");

			for (int i = 0; i < agentes.getLength(); i++) {
				Element agente = (Element) agentes.item(i);
				Element codigo = (Element) agente.getElementsByTagName("codigo").item(0);

				if (codigo != null) {
					String agenteCodigo = codigo.getTextContent();
					codigosAgentes.add(agenteCodigo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return codigosAgentes;
	}

	public List<String> extrairDatasAgente(String arquivoXml) {
		List<String> datasAgentes = new ArrayList<>();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(arquivoXml)));

			NodeList agentes = document.getElementsByTagName("agente");

			for (int i = 0; i < agentes.getLength(); i++) {
				Element agente = (Element) agentes.item(i);
				Element data = (Element) agente.getElementsByTagName("data").item(0);

				if (data != null) {
					String agenteData = data.getTextContent();
					datasAgentes.add(agenteData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datasAgentes;
	}
	
	public List<String> extrairRegioesAgente(String arquivoXml) {
		List<String> regioesAgentes = new ArrayList<>();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(arquivoXml)));

			NodeList agentes = document.getElementsByTagName("agente");

			for (int i = 0; i < agentes.getLength(); i++) {
				Element agente = (Element) agentes.item(i);
				Element regiao = (Element) agente.getElementsByTagName("regiao").item(0);

				if (regiao != null) {
					String agenteRegiao = regiao.getTextContent();
					regioesAgentes.add(agenteRegiao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return regioesAgentes;
	}

}
