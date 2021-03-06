package models;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Manutencao {

	int id;
	Date data;
	String equipamento;
	Double custoHora;
	Double tempoGasto;

	private DecimalFormat df = new DecimalFormat("#.00");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Manutencao(int id, String data, String equipamento, String custoHora, String tempoGasto) {
		try {
			this.id = id;
			this.data = sdf.parse(data);
			this.equipamento = equipamento;
			this.custoHora = Double.parseDouble(df.parse(custoHora).toString());
			this.tempoGasto = Double.parseDouble(df.parse(tempoGasto).toString());
		} catch (ParseException e) {
			System.out.println(e);
		}
	}

	public Manutencao(String linha) {
		try {
			this.id = Integer.parseInt(linha.split(";")[0]);
			this.data = sdf.parse(linha.split(";")[1]);
			this.equipamento = linha.split(";")[2];
			this.custoHora = Double.parseDouble(df.parse(linha.split(";")[3]).toString());
			this.tempoGasto = Double.parseDouble(df.parse(linha.split(";")[4]).toString());
		} catch (ParseException e) {
			System.out.println(e);
		}

	}

	public Manutencao(int id, String equipamento, String custoHora, String tempoGasto) {
		try {
			this.id = id;
			this.data = new Date();
			this.equipamento = equipamento;
			this.custoHora = Double.parseDouble(df.parse(custoHora).toString());
			this.tempoGasto = Double.parseDouble(df.parse(tempoGasto).toString());
		} catch (ParseException e) {
			System.out.println(e);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public Double getCustoHora() {
		return custoHora;
	}

	public void setCustoHora(Double custoHora) {
		this.custoHora = custoHora;
	}

	public Double getTempoGasto() {
		return tempoGasto;
	}

	public void setTempoGasto(Double tempoGasto) {
		this.tempoGasto = tempoGasto;
	}

	public Double getTotal() {
		return getCustoHora() * getTempoGasto();
	}

	public int hashCode() {
		return Objects.hash(custoHora, data, equipamento, id, tempoGasto);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manutencao other = (Manutencao) obj;
		return Objects.equals(custoHora, other.custoHora) && Objects.equals(data, other.data)
				&& Objects.equals(equipamento, other.equipamento) && id == other.id
				&& Objects.equals(tempoGasto, other.tempoGasto);
	}

	public String toCSV() {
		return id + ";" + sdf.format(data) + ";" + equipamento + ";" + df.format(custoHora) + ";"
				+ df.format(tempoGasto) + "\r\n";
	}

	public String toString() {
		return id + "\t" + sdf.format(data) + "\t" + equipamento + "\t" + df.format(custoHora) + "\t"
				+ df.format(tempoGasto) + "\t" + getTotal() + "\n";
	}

}
