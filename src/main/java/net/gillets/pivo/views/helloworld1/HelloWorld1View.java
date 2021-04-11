package net.gillets.pivo.views.helloworld1;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.gillets.pivo.views.main.MainView;

@Route(value = "hello-world", layout = MainView.class)
@PageTitle("Hello World1")
@CssImport("./styles/views/helloworld1/hello-world1-view.css")
public class HelloWorld1View extends HorizontalLayout {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -4910968789916082903L;

    private TextField name;
    private Button sayHello;

    public HelloWorld1View() {
        setId("hello-world1-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> Notification.show("Hello World " + name.getValue()));
    }

}
