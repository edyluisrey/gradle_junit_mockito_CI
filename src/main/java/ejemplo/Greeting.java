package ejemplo;

import interfaces.ITranslator;

public class Greeting {
    private ITranslator translator;

    public Greeting(ITranslator translator) {
            this.translator = translator;
    }

    public String sayHello(String language, String name) {
            return translator.translate("English", language, "Hello") + " " + name;
    }
}
