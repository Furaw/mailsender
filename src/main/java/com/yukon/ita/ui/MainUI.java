package com.yukon.ita.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import com.yukon.ita.mail.MailResponse;
import com.yukon.ita.mail.MailServices;
import com.yukon.ita.recipient.Recipient;
import com.yukon.ita.recipient.RecipientService;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Path;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


@Route("")
@CssImport("./styles/styles.css")
public class MainUI extends VerticalLayout{

    @Autowired
    private MailServices mailServices;
    @Autowired
    RecipientService recipientService;
    private final H1 mainText = new H1("Mail Sender");
    private final TextArea textArea = new TextArea("", "FTL template:");
    private final TextArea jsonArea = new TextArea("", "JSON:");
    private Button sendButton;
    private final MemoryBuffer htmlMemoryBuffer = new MemoryBuffer();
    private final MemoryBuffer jsonMemoryBuffer = new MemoryBuffer();
    private final Upload uploadTemplate = new Upload(htmlMemoryBuffer);
    private final Upload uploadJson = new Upload(jsonMemoryBuffer);
    private final VerticalLayout leftPart = new VerticalLayout();
    private final H2 h2one = new H2("Your mail successfully send");
    private final H2 h2two = new H2("Error sending mail");
    private Button singInButton;
    private Button saveChanges;
    private  Button saveFTL;
    private Button singOutButton;
    private final TextField userMail = new TextField("", "Your email: ");
    private final PasswordField userPassword = new PasswordField("", "Your password: ");

    public MainUI() {
        saveChanges = new Button("Save changes", clik -> {
            try {
                saveJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        saveFTL = new Button("Save changes", clik -> {
            try {
                saveFTL();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        singOutButton = new Button("Sign out", clik -> {
            singOutButtonClick();
        });
        singOutButton.setVisible(false);
        singInButton = new Button("Sign in", clik -> {
            SingInButtonClick();
        });
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        textArea.setValueChangeMode(ValueChangeMode.EAGER);
        Icon icon = VaadinIcon.ENVELOPE_O.create();
        sendButton = new Button("Send mail", icon, clik -> {
            try {
                SendButtonClick();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        sendButton.setWidth("165px");
        sendButton.setHeight("60px");

        addComponents();
    }

    private void SendButtonClick() throws JSONException {
        sendingMail();
        h2one.setVisible(false);
        h2two.setVisible(false);
        if (MailResponse.getStatus() == true) {
            h2one.setVisible(true);
            add(h2one);
        } else {
            h2two.setVisible(true);
            add(h2two);
        }
    }

    private void addComponents() {
        Header();
        VerticalLayout centerPart = new VerticalLayout(jsonArea, uploadJson,saveChanges);

        VerticalLayout rightPart = new VerticalLayout(textArea, uploadTemplate,saveFTL);

        HorizontalLayout content = new HorizontalLayout(leftPart, centerPart, rightPart);
        content.setSizeFull();
        leftPart.setSizeFull();
        add(content);
        content.setSizeFull();
        textArea.setWidth("500px");
        textArea.setHeight("500px");
        jsonArea.setWidth("500px");
        jsonArea.setHeight("500px");
        saveChanges.setHeight("50px");
        saveChanges.setWidth("200px");
        saveFTL.setHeight("50px");
        saveFTL.setWidth("200px");
        textArea.addValueChangeListener(event -> showHTML(event.getValue()));
        uploadTemplate.addSucceededListener(e -> {
            uploadHtmlEvent();
        });
        uploadJson.addSucceededListener(e -> {
            uploadJsonEvent();
        });
    }

    private void Header() {
        HorizontalLayout registration = new HorizontalLayout(userMail, userPassword, singInButton, singOutButton);
        HorizontalLayout tittle = new HorizontalLayout(mainText);
        HorizontalLayout header = new HorizontalLayout(tittle, registration);
        userMail.setWidth("210px");
        userPassword.setWidth("210px");
        registration.setSizeFull();
        tittle.setSizeFull();
        header.setSizeFull();
        add(header);
        ThemeList themeList1 = registration.getThemeList();
        themeList1.add(Lumo.DARK);
        ThemeList themeList2 = tittle.getThemeList();
        themeList2.add(Lumo.DARK);
        ThemeList themeList3 = header.getThemeList();
        themeList3.add(Lumo.DARK);
    }

    private void uploadHtmlEvent() {
        try {
            InputStream inputStream = htmlMemoryBuffer.getInputStream();
            String content = readFile(inputStream);
            textArea.setValue(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void uploadJsonEvent() {
        try {
            InputStream inputStream = jsonMemoryBuffer.getInputStream();
            String content = readFile(inputStream);

            jsonArea.setValue(content);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String readFile(InputStream inputStream) throws IOException {
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    private void singOutButtonClick() {
        singInButton.setVisible(true);
        userPassword.setVisible(true);
        userMail.clear();
        userPassword.clear();
        mailServices.setDefaultBean();
        singOutButton.setVisible(false);
    }

    public void SingInButtonClick() {
        mailServices.setNewBean(userMail.getValue(), userPassword.getValue());
        singInButton.setVisible(false);
        userPassword.setVisible(false);
        singOutButton.setVisible(true);
    }

    private void showHTML(String htmlString) {
        htmlString = Strings.isBlank(htmlString) ? "" : htmlString;
        leftPart.removeAll();
        Html html = new Html(htmlString);
        leftPart.add(html, sendButton);
    }

    private void sendingMail() throws JSONException {
        Recipient recipient = new Recipient();

        String str = null;
        try {
            str = readFile(jsonMemoryBuffer.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray obj = new JSONArray(str);
        for (int i = 0; i < obj.length(); i++) {
            recipient.setRecipientEmail(obj.getJSONObject(i).getString("recipientEmail"));
            recipient.setRecipientSubject(obj.getJSONObject(i).getString("recipientSubject"));
            recipient.setRecipientText(textArea.getValue());
            Recipient recipient1 = new Recipient(recipient.getRecipientEmail());
            recipientService.insert(recipient1);
            Map model = new HashMap();
            model.put("recipientSubject", recipient.getRecipientEmail());
            model.put("recipientText", recipient.getRecipientText());
            model.put("recipientEmail", recipient.getRecipientSubject());

            mailServices.sendSimpleMessage(recipient, model);
        }
    }

    private void saveJson() throws IOException
    {
        String fileContent = jsonArea.getValue();
        RandomAccessFile stream = new RandomAccessFile("/home/andrusha/Documents/mailsender-master/src/main/resources/templates/email_test.txt", "rw");
        FileChannel channel = stream.getChannel();
        byte[] strBytes = fileContent.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();
    }
    private void saveFTL() throws IOException
    {
        String fileContent = textArea.getValue();
        RandomAccessFile stream = new RandomAccessFile("/home/andrusha/Documents/mailsender-master/src/main/resources/templates/email_test.ftl", "rw");
        FileChannel channel = stream.getChannel();
        byte[] strBytes = fileContent.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();
    }

}
