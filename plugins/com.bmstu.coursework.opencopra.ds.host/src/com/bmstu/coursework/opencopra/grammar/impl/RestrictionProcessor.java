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
public class RestrictionProcessor
    extends AbstractProcessor {

    public String type;
    public String auto;
    public String left;
    public String right;
    public String left_type;
    public String right_type;

    public Element rootNode;
    public Document document;


    /* Конструктор */
    public RestrictionProcessor() {

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
		rootNode = document.getRootElement().getChild("restrictions"); //$NON-NLS-1$
    }

    /* Создание нового ограничения */
    public String create(String left, String right, List<String> params) {

        this.type = params.get(0);
        this.auto = params.get(1);
        this.left_type = params.get(2);
        this.right_type = params.get(3);

        if (this.type.length() == 0 || this.auto.length() == 0 || this.left_type.length() == 0
            || this.left_type.length() == 0)
        {
			return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

		Element rs = new Element("restr"); //$NON-NLS-1$
		rs.setAttribute("type", this.type); //$NON-NLS-1$
		rs.setAttribute("auto", this.auto); //$NON-NLS-1$

		Element rsLeft = new Element("left"); //$NON-NLS-1$
		rsLeft.setAttribute("left_type", this.left_type); //$NON-NLS-1$
        rsLeft.addContent(left);

		Element rsRight = new Element("right"); //$NON-NLS-1$
		rsRight.setAttribute("right_type", this.right_type); //$NON-NLS-1$
        rsRight.addContent(right);

        rs.addContent(rsLeft);
        rs.addContent(rsRight);

        rootNode.addContent(rs);

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

		return "Restriction with type: " + this.type + " auto: " + this.auto + " left type: " + this.left_type //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		    + " left: " + left + " right type: " + this.right_type + " right: " + right //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		    + " successfully added"; //$NON-NLS-1$
    }

    /* Чтение существующего ограничения */
    public String read(String left, String right) {

		List<Element> list = rootNode.getChildren("restr"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
			if (list.get(i).getChildText("left").equals(left) && list.get(i).getChildText("right").equals(right)) //$NON-NLS-1$ //$NON-NLS-2$
            {
				this.type = list.get(i).getAttributeValue("type"); //$NON-NLS-1$
				this.auto = list.get(i).getAttributeValue("auto"); //$NON-NLS-1$
				this.left_type = list.get(i).getChild("left").getAttributeValue("type"); //$NON-NLS-1$ //$NON-NLS-2$
				this.right_type = list.get(i).getChild("right").getAttributeValue("type"); //$NON-NLS-1$ //$NON-NLS-2$

				return "There is restriction with type: " + this.type + " auto: " + this.auto + " left type: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				    + this.left_type + " left: " + left + " right type: " + this.right_type + " right: " + right; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
        }

		return "There is no restriction with left: " + left + " and right: " + right; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /* Изменение существующего ограничения */
    public String update(String left, String right, List<String> params) {

        this.type = params.get(0);
        this.auto = params.get(1);
        this.left_type = params.get(2);
        this.right_type = params.get(3);

        if (this.type.length() == 0 || this.auto.length() == 0 || this.left_type.length() == 0
            || this.left_type.length() == 0)
        {
			return "Error: You have missed some parameter(s)"; //$NON-NLS-1$
        }

		List<Element> list = rootNode.getChildren("restr"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
			if (list.get(i).getChildText("left").equals(left) && list.get(i).getChildText("right").equals(right)) //$NON-NLS-1$ //$NON-NLS-2$
            {
				list.get(i).setAttribute("type", this.type); //$NON-NLS-1$
				list.get(i).setAttribute("auto", this.auto); //$NON-NLS-1$
				list.get(i).getChild("left").setAttribute("type", this.left_type); //$NON-NLS-1$ //$NON-NLS-2$
				list.get(i).getChild("right").setAttribute("type", this.right_type); //$NON-NLS-1$ //$NON-NLS-2$

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

				return "Restriction with left: " + left + " and right: " + right + " successfully updated"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
        }

		return "There is no restriction with left: " + left + " and right: " + right; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /* Удаление существующего ограничения */
    public String delete(String left, String right) {

		List<Element> list = rootNode.getChildren("restr"); //$NON-NLS-1$

        for (int i = 0; i < list.size(); i++)
        {
			if (list.get(i).getChildText("left").equals(left) && list.get(i).getChildText("right").equals(right)) //$NON-NLS-1$ //$NON-NLS-2$
            {
				this.type = list.get(i).getAttributeValue("type"); //$NON-NLS-1$
				this.auto = list.get(i).getAttributeValue("auto"); //$NON-NLS-1$
				this.left_type = list.get(i).getChild("left").getAttributeValue("type"); //$NON-NLS-1$ //$NON-NLS-2$
				this.right_type = list.get(i).getChild("right").getAttributeValue("type"); //$NON-NLS-1$ //$NON-NLS-2$

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

				return "Restriction with left: " + left + " right: " + right + " successfully deleted"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
        }

		return "There is no restriction with left: " + left + " and right: " + right; //$NON-NLS-1$ //$NON-NLS-2$
    }
}