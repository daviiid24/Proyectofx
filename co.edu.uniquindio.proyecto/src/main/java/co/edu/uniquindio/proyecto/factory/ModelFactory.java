package co.edu.uniquindio.proyecto.factory;

import co.edu.uniquindio.proyecto.model.Gimnasio;
import co.edu.uniquindio.proyecto.utils.DataUtil;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Gimnasio gimnasio;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private ModelFactory(){
        gimnasio = DataUtil.inicializarDatos();
    }
}
