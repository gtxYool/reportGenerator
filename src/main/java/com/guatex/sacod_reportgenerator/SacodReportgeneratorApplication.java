package com.guatex.sacod_reportgenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.guatex.sacod_reportgenerator.Objetos.Cliente;
import com.guatex.sacod_reportgenerator.datos.FacClientes_Querys;
import com.guatex.sacod_reportgenerator.pdf.FacturaReport;
import com.guatex.sacod_reportgenerator.pdf.GuiasCODReport;
import com.guatex.sacod_reportgenerator.pdf.AcreditacionReport;

@SpringBootApplication
@RestController
@RequestMapping("/PdfGenerator")
public class SacodReportgeneratorApplication {

	public static final Logger logger = LogManager.getLogger(SacodReportgeneratorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SacodReportgeneratorApplication.class, args);
	}

	@PostMapping("/FacturacionReport")
	public boolean facturacionReport(@RequestBody String Alldata) {
		try {
			JSONArray allData = new JSONArray(Alldata);
			JSONObject data = (JSONObject) allData.get(0);
			JSONArray objects = (JSONArray) allData.get(1);
			if (!data.toString().isEmpty() && data.toString().length() > 3) {
				String[] encabezados = data.getString("encabezados").split(",");
				String[] atributos = data.getString("atributos").split(",");
				String titulo = data.getString("titulo");
				String nofact = data.getString("nofact");
				String idreporte = data.getString("idreporte");
				String codigo = data.getString("codigo");
				String[] operar = data.getString("operar").split(",");

				TableReport tb = new TableReport(encabezados, atributos, objects.toString(), titulo);
				Cliente cliente = new FacClientes_Querys().getCliente(codigo);
				// for (String st : operar) {
				tb.addOperation(operar);
				// }
				new FacturaReport(codigo + idreporte).create(tb, cliente, nofact);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Algo malio sal... err: " + e.getMessage() + "\n");
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/GuiasReport")
	public boolean GuiasReport(@RequestBody String Alldata) {
		System.out.println(Alldata);
		try {
			JSONArray allData = new JSONArray(Alldata);
			JSONObject data = (JSONObject) allData.get(0);
			JSONArray objects = (JSONArray) allData.get(1);
			if (!data.toString().isEmpty() && data.toString().length() > 3) {
				String[] encabezados = data.getString("encabezados").split(",");
				String[] atributos = data.getString("atributos").split(",");
				String titulo = data.getString("titulo");
				//String nofact = data.getString("nofact");
				//String idreporte = data.getString("idreporte");
				String codigo = data.getString("codigo");
				String[] operar = data.getString("operar").split(",");

				TableReport tb = new TableReport(encabezados, atributos, objects.toString(), titulo);
				Cliente cliente = new FacClientes_Querys().getCliente(codigo);
				// for (String st : operar) {
				tb.addOperation(operar);
				// }
				new GuiasCODReport(codigo+"_ReporteGuias").create(tb, cliente);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Algo malio sal... err: " + e.getMessage() + "\n");
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping("/AcreditacionReport")
	public boolean acreditacionReport(@RequestBody String Alldata) {
		System.out.println(Alldata);
		try {
			JSONArray allData = new JSONArray(Alldata);
			JSONObject data = (JSONObject) allData.get(0);
			JSONArray objects = (JSONArray) allData.get(1);
			JSONArray ajustes = (JSONArray) allData.get(2);
			if (!data.toString().isEmpty() && data.toString().length() > 3) {
				String[] encabezados = data.getString("encabezados").split(",");
				String[] atributos = data.getString("atributos").split(",");
				String titulo = data.getString("titulo");
				String idreporte = data.getString("idreporte");
				String codigo = data.getString("codigo");
				String[] operar = data.getString("operar").split(",");
				String idACH = data.getString("idach");
				String totalisimo=data.getString("totalisimo");
				TableReport tbAjustes=null;
				TableReport tb = new TableReport(encabezados, atributos, objects.toString(), titulo);
				if(!ajustes.isEmpty()) {
					tbAjustes = new TableReport("Tipo,Guia,Monto".split(","),
							"tipo,noguia,monto".split(","), ajustes.toString(), "Ajustes");
					tbAjustes.addOperation("monto".split(","));
				}
		
				Cliente cliente = new FacClientes_Querys().getCliente(codigo);
				// for (String st : operar) {
				tb.addOperation(operar);
				// }
				new AcreditacionReport(codigo + idreporte).create(tb, tbAjustes,cliente, idACH,totalisimo);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Algo malio sal... err: " + e.getMessage() + "\n");
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/FacturacionReport_R")
	public boolean facturacionReport_R(@RequestBody String Alldata) {
		try {
			JSONArray allData = new JSONArray(Alldata);
			JSONObject data = (JSONObject) allData.get(0);
			JSONArray objects = (JSONArray) allData.get(1);
			if (!data.toString().isEmpty() && data.toString().length() > 3) {
				String[] encabezados = data.getString("encabezados").split(",");
				String[] atributos = data.getString("atributos").split(",");
				String titulo = data.getString("titulo");
				String idreporte = data.getString("idreporte");
				String codigo = data.getString("codigo");
				String[] operar = data.getString("operar").split(",");
				String banco = data.getString("banco");
				String noCuenta = data.getString("noCuenta");
				String tipoCuenta = data.getString("tipoCuenta");
				String nofact = data.getString("nofact");
				TableReport tb = new TableReport(encabezados, atributos, objects.toString(), titulo);
				Cliente cliente = new FacClientes_Querys().getCliente(codigo);
				cliente.setBanco(banco);
				cliente.setNoCuenta(noCuenta);
				cliente.setTipoCuenta(tipoCuenta);
				// for (String st : operar) {
				tb.addOperation(operar);
				// }
				new FacturaReport(codigo + idreporte).create(tb, cliente, nofact);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Algo malio sal... err: " + e.getStackTrace() + "\n");
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/AcreditacionReport_R")
	public boolean acreditacionReport_R(@RequestBody String Alldata) {
		System.out.println(Alldata);
		try {
			JSONArray allData = new JSONArray(Alldata);
			JSONObject data = (JSONObject) allData.get(0);
			JSONArray objects = (JSONArray) allData.get(1);
			if (!data.toString().isEmpty() && data.toString().length() > 3) {
				String[] encabezados = data.getString("encabezados").split(",");
				String[] atributos = data.getString("atributos").split(",");
				String titulo = data.getString("titulo");
				String idreporte = data.getString("idreporte");
				String codigo = data.getString("codigo");
				String banco = data.getString("banco");
				String noCuenta = data.getString("noCuenta");
				String tipoCuenta = data.getString("tipoCuenta");
				String[] operar = data.getString("operar").split(",");
				String idACH = data.getString("idach");
				TableReport tb = new TableReport(encabezados, atributos, objects.toString(), titulo);
				Cliente cliente = new FacClientes_Querys().getCliente(codigo);
				cliente.setBanco(banco);
				cliente.setNoCuenta(noCuenta);
				cliente.setTipoCuenta(tipoCuenta);
				// for (String st : operar) {
				tb.addOperation(operar);
				// }
				//new AcreditacionReport(codigo + idreporte).create(tb, cliente, idACH);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Algo malio sal... err: " + e.getMessage() + "\n");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param codcob
	 * @return
	 */
	@GetMapping("/getDataBanc")
	public Cliente getDataBanc(@RequestParam String codcob) {
		FacClientes_Querys fc = new FacClientes_Querys();
		return fc.getDataBanc(fc.getCliente(codcob));
	}

}
