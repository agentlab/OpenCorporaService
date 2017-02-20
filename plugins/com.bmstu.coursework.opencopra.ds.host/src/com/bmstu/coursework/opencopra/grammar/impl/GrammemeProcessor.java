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
public class GrammemeProcessor
    extends AbstractProcessor {

    public String parent;
    public String name;
    public String alias;
    public String description;

    public Element rootNode;
    public Document document;


    /* Конструктор */
    public GrammemeProcessor() {

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
        rootNode = document.getRootElement().getChild("grammemes"); //$NON-NLS-1$
    }

    /* Создание новой граммемы */
    public String create(String name, List<String> params) {

        this.parent = params.get(0);
        this.alias = params.get(1);
        this.description = params.get(2);

        if (name.length() == 0 || this.alias.length() == 0 || this.description.length() == 0)
        {
            return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

        Element gr = new Element("grammeme"); //$NON-NLS-1$
        gr.setAttribute("parent", parent); //$NON-NLS-1$

        Element grName = new Element("name"); //$NON-NLS-1$
        grName.addContent(name);

        Element grAlias = new Element("alias"); //$NON-NLS-1$
        grAlias.addContent(this.alias);

        Element grDescription = new Element("description"); //$NON-NLS-1$
        grDescription.addContent(this.description);

        gr.addContent(grName);
        gr.addContent(grAlias);
        gr.addContent(grDescription);

        rootNode.addContent(gr);

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

        return "Grammeme with parent: " + this.parent + " name: " + name + " alias: " + this.alias //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + " description: " + this.description + " successfully added"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /* Чтение существующей граммемы */
    public String read(String name) {

        List<Element> list = rootNode.getChildren("grammeme"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getChildText("name").equals(name)) //$NON-NLS-1$
            {
                this.parent = list.get(i).getAttributeValue("parent"); //$NON-NLS-1$
                this.alias = list.get(i).getChildText("alias"); //$NON-NLS-1$
                this.description = list.get(i).getChildText("description"); //$NON-NLS-1$

                return "There is grammeme with parent: " + this.parent + " name: " + name + " alias: " + this.alias //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    + " description: " + this.description; //$NON-NLS-1$
            }
        }

        return "There is no grammema with name: " + name; //$NON-NLS-1$
    }

    /* Изменение существующей граммемы */
    public String update(String name, List<String> params) {

        this.parent = params.get(0);
        this.alias = params.get(1);
        this.description = params.get(2);

        if (name.length() == 0 || this.alias.length() == 0 || this.description.length() == 0)
        {
            return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

        List<Element> list = rootNode.getChildren("grammeme"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getChildText("name").equals(name)) //$NON-NLS-1$
            {
                list.get(i).setAttribute("parent", this.parent); //$NON-NLS-1$
                list.get(i).getChild("alias").setText(this.alias); //$NON-NLS-1$
                list.get(i).getChild("description").setText(this.description); //$NON-NLS-1$

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

                return "Grammeme with name: " + name + " successfully updated"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

        return "There is no grammema with name: " + name; //$NON-NLS-1$
    }

    /* Удаление существующей граммемы */
    public String delete(String name) {

        List<Element> list = rootNode.getChildren("grammeme"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getChildText("name").equals(name)) //$NON-NLS-1$
            {
                this.parent = list.get(i).getAttributeValue("parent"); //$NON-NLS-1$
                this.alias = list.get(i).getChildText("alias"); //$NON-NLS-1$
                this.description = list.get(i).getChildText("description"); //$NON-NLS-1$

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

                return "Grammeme with name: " + name + " successfully deleted"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

        return "There is no grammema with name: " + name; //$NON-NLS-1$
    }
}
