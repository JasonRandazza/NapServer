package se.lnu.os.ht23.a1.provided.impl;

import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.RegistrarType;

public class RegistrarFactoryImpl {

	public static Registrar createRegistrar(RegistrarType type) {

		Registrar r;

		switch (type) {

		case LABORIOUS:
			r = RegistrarLaboriousImpl.create();
			break;
		case LAZY:
			r = RegistrarLazyImpl.create();
			r.setSleep((int) (Math.random() * 3000));
			break;
		default:
			r = RegistrarLaboriousImpl.create();
		}
		return r;

	}
	
	public static Registrar createRegistrar(RegistrarType type, int lazinessTime) {

		Registrar r;

		switch (type) {

		case LABORIOUS:
			throw new UnsupportedOperationException("LABORIOUS registrar type does not take lazy naps");
		case LAZY:
			r = RegistrarLazyImpl.create();
			r.setSleep(lazinessTime);
			break;
		default:
			r = RegistrarLaboriousImpl.create();
		}
		return r;

	}

}
