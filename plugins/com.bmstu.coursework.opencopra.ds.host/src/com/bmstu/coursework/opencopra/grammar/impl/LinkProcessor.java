/**
 *
 */
package com.bmstu.coursework.opencopra.grammar.impl;

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

import com.bmstu.coursework.opencopra.grammar.AbstractProcessor;

/**
 *
 * @Naymushin
 *
 */
public class LinkProcessor
    extends AbstractProcessor {
    public String id;
    public String from;
    public String to;
    public String type;

    public Element rootNode;
    public Document document;

    /* Конструктор */
    public LinkProcessor() {

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
        rootNode = document.getRootElement().getChild("links"); //$NON-NLS-1$
    }

    /* Создание новой ссылки */
    public String create(String id, List<String> params) {

        this.from = params.get(0);
        this.to = params.get(1);
        this.type = params.get(2);

        if (this.from.length() == 0 || this.to.length() == 0 || this.type.length() == 0)
        {
            return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

        Element lk = new Element("link"); //$NON-NLS-1$

        lk.setAttribute("id", id); //$NON-NLS-1$
        lk.setAttribute("from", this.from); //$NON-NLS-1$
        lk.setAttribute("to", this.to); //$NON-NLS-1$
        lk.setAttribute("type", this.type); //$NON-NLS-1$

        rootNode.addContent(lk);

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

        return "Link with id: " + id + " from: " + this.from + " to: " + this.to + " type: " + this.type //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            + " successfully added"; //$NON-NLS-1$
    }

    /* Чтение существующей ссылки */
    public String read(String id) {

        List<Element> list = rootNode.getChildren("link"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getAttributeValue("id").equals(id)) //$NON-NLS-1$
            {
                this.from = list.get(i).getAttributeValue("from"); //$NON-NLS-1$
                this.to = list.get(i).getAttributeValue("to"); //$NON-NLS-1$
                this.type = list.get(i).getAttributeValue("type"); //$NON-NLS-1$

                return "There is link with id: " + id + " from: " + this.from + " to: " + this.to + " type: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                    + this.type;
            }
        }

        return "There is no link with id: " + id; //$NON-NLS-1$
    }

    /* Изменение существующей ссылки */
    public String update(String id, List<String> params) {

        this.from = params.get(0);
        this.to = params.get(1);
        this.type = params.get(2);

        if (this.from.length() == 0 || this.to.length() == 0 || this.type.length() == 0)
        {
            return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

        List<Element> list = rootNode.getChildren("link"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getAttributeValue("id").equals(id)) //$NON-NLS-1$
            {
                list.get(i).setAttribute("from", this.from); //$NON-NLS-1$
                list.get(i).setAttribute("to", this.to); //$NON-NLS-1$
                list.get(i).setAttribute("type", this.type); //$NON-NLS-1$

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

                return "Link with id: " + id + " successfully updated"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

        return "There is no link with id: " + id; //$NON-NLS-1$
    }

    /* Удаление существующей ссылки */
    public String delete(String id) {

        List<Element> list = rootNode.getChildren("link"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getAttributeValue("id").equals(id)) //$NON-NLS-1$
            {
                this.from = list.get(i).getAttributeValue("from"); //$NON-NLS-1$
                this.to = list.get(i).getAttributeValue("to"); //$NON-NLS-1$
                this.type = list.get(i).getAttributeValue("type"); //$NON-NLS-1$

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

                return "Link with id: " + id + " successfully deleted"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

        return "There is no link with id: " + id; //$NON-NLS-1$
    }
}
