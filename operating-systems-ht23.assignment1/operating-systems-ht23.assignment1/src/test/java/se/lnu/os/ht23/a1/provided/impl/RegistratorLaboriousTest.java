package se.lnu.os.ht23.a1.provided.impl;

import se.lnu.os.ht23.a1.provided.data.RegistrarType;

public class RegistratorLaboriousTest extends RegistratorTest {

	@Override
	public void defineRegistrar() {
		r = RegistrarFactoryImpl.createRegistrar(RegistrarType.LABORIOUS);

	}

}
