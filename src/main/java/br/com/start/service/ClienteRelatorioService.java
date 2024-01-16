package br.com.start.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.start.model.QuantidadePorClassificacaoCliente;
import br.com.start.model.QuantidadePorTipoConta;
import br.com.start.repository.ClienteRepository;

@Service
public class ClienteRelatorioService {

	@Autowired
	private ClienteRepository repository;

	public byte[] quantidadePorTipoContaImagem() throws IOException {
		try (ByteArrayOutputStream arquivo = new ByteArrayOutputStream()) {
			DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

			for (QuantidadePorTipoConta quantidadePorTipoConta : repository.quantidadePorTipoConta()) {

				dataset.setValue(quantidadePorTipoConta.getTipoConta().toString(),
						quantidadePorTipoConta.getQuantidade());
			}
			JFreeChart grafico = ChartFactory.createPieChart("Clientes Por Tipo da conta", // titulo
					dataset, true, // legenda
					true, false);

			PiePlot<?> plot = (PiePlot<?>) grafico.getPlot();

			PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{1} - {2}", new DecimalFormat("0"),
					new DecimalFormat("0%"));
			plot.setLabelGenerator(gen);
			plot.setSimpleLabels(true);

			ChartUtils.writeChartAsPNG(arquivo, grafico, 550, 400);

			return arquivo.toByteArray();
		}

	}

	public byte[] quantidadePorClassificacaoClienteImagem() throws IOException {
		try (ByteArrayOutputStream arquivo = new ByteArrayOutputStream()) {
			DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

			for (QuantidadePorClassificacaoCliente quantidadePorClassificacao : repository
					.quantidadePorClassificacaoCliente()) {

				dataset.setValue(quantidadePorClassificacao.getClassificacao().toString(),
						quantidadePorClassificacao.getQuantidade());
			}
			JFreeChart grafico = ChartFactory.createPieChart("Clientes por Classificacao da Conta", // titulo
					dataset, true, // legenda
					true, false);

			PiePlot<?> plot = (PiePlot<?>) grafico.getPlot();

			PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{1} - {2}", new DecimalFormat("0"),
					new DecimalFormat("0%"));
			plot.setLabelGenerator(gen);
			plot.setSimpleLabels(true);

			ChartUtils.writeChartAsPNG(arquivo, grafico, 550, 400);

			return arquivo.toByteArray();

		}
	}
}
