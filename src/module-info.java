module atividadePOO_Parte2 {
    requires java.desktop;
	requires PersistenciaObjetos;
	requires org.junit.jupiter.api;
	requires oblesqbom;
	requires junit;
    exports br.gov.cesarschool.poo.bonusvendas.telas;
    exports br.gov.cesarschool.poo.bonusvendas.testes to junit;
}