/**
 *
 */
package com.bmstu.coursework.opencopra.ds.host;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @Naymushin
 *
 */
public class LinkTypeProcessor {
    public String id;
    public String type;

    public Element rootNode;
    public Document document;

	public final String OLD_PATH = "e://kurs//dict.xml"; //$NON-NLS-1$
	public final String NEW_PATH = "e://kurs//dict_new.xml"; //$NON-NLS-1$

    /* Конструктор */
    public LinkTypeProcessor() {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(OLD_PATH);

        try
        {
            document = builder.build(xmlFile);
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		rootNode = document.getRootElement().getChild("link_types"); //$NON-NLS-1$
    }

    /* Создание новой типа ссылки */
    public String create(String id, List<String> params) {

        this.type = params.get(0);

        if (this.type.length() == 0)
        {
			return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

		Element tp = new Element("type"); //$NON-NLS-1$

		tp.setAttribute("id", id); //$NON-NLS-1$
        tp.addContent(this.type);

        rootNode.addContent(tp);

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        try
        {
            xmlOutput.output(document, new FileWriter(NEW_PATH));
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		return "Link type with id: " + id + " type: " + this.type + " successfully added"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /* Чтение существующего типа ссылки */
    public String read(String id) {

		List<Element> list = rootNode.getChildren("type"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
			if (list.get(i).getAttribute("id").equals(id)) //$NON-NLS-1$
            {
                this.type = list.get(i).getText();

				return "There is link type with id: " + id + " type: " + this.type; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

		return "There is no link type with id: " + id; //$NON-NLS-1$
    }

    /* Изменение существующей ссылки */
    public String update(String id, List<String> params) {

        this.type = params.get(0);

        if (this.type.length() == 0)
        {
			return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

		List<Element> list = rootNode.getChildren("type"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
			if (list.get(i).getAttribute("id").equals(id)) //$NON-NLS-1$
            {
                list.get(i).setText(this.type);

                XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getPrettyFormat());

                try
                {
                    xmlOutput.output(document, new FileWriter(NEW_PATH));
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

				return "Link type with id: " + id + " successfully updated"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

		return "There is no link type with id: " + id; //$NON-NLS-1$
    }

    /* Удаление существующей ссылки */
    public String delete(String id) {

		List<Element> list = rootNode.getChildren("type"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
			if (list.get(i).getAttribute("id").equals(id)) //$NON-NLS-1$
            {
                this.type = list.get(i).getText();

                list.get(i).detach();

                XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getPrettyFormat());

                try
                {
                    xmlOutput.output(document, new FileWriter(NEW_PATH));
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

				return "Link type with id: " + id + " successfully deleted"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

		return "There is no link type with id: " + id; //$NON-NLS-1$
    }
}