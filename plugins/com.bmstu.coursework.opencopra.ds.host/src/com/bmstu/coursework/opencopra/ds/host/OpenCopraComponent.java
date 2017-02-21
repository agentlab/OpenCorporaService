/**
 *
 */
package com.bmstu.coursework.opencopra.ds.host;

import java.io.IOException;
import java.util.Dictionary;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import com.bmstu.coursework.opencopra.IOpenCopraService;
import com.bmstu.coursework.opencopra.grammar.impl.GrammemeProcessor;
import com.bmstu.coursework.opencopra.grammar.impl.LemmaProcessor;
import com.bmstu.coursework.opencopra.grammar.impl.LinkProcessor;
import com.bmstu.coursework.opencopra.grammar.impl.LinkTypeProcessor;
import com.bmstu.coursework.opencopra.grammar.impl.RestrictionProcessor;

/**
 *
 * Oomph component realisation.
 *
 * @author Prihodko
 *
 */
@Component(enabled = true, immediate = true,
    property = { "service.exported.interfaces=*", "service.exported.configs=ecf.jaxrs.jersey.server",
        "ecf.jaxrs.jersey.server.urlContext=http://localhost:8080", "ecf.jaxrs.jersey.server.alias=/",
        "service.pid=com.bmstu.coursework.oomph.ds.host.OomphComponent" })
public class OpenCopraComponent
    implements IOpenCopraService, ManagedService {

    /* Метод Create */
    @Override
    public Response post(String service, String action, String section, String name, String left, String right,
        String id, List<String> params) {

        switch (section) {
        case "grammemes": //$NON-NLS-1$
            GrammemeProcessor gmp = new GrammemeProcessor();
            return Response.ok().entity(gmp.create(name, params)).build();

        case "restrictions": //$NON-NLS-1$
            RestrictionProcessor rtp = new RestrictionProcessor();
            return Response.ok().entity(rtp.create(left, right, params)).build();

        case "lemmata": //$NON-NLS-1$
            LemmaProcessor lmp = new LemmaProcessor();
            return Response.ok().entity(lmp.create(id, params)).build();

        case "link_types": //$NON-NLS-1$
            LinkTypeProcessor ltp = new LinkTypeProcessor();
            return Response.ok().entity(ltp.create(id, params)).build();

        case "links": //$NON-NLS-1$
            LinkProcessor lkp = new LinkProcessor();
            return Response.ok().entity(lkp.create(id, params)).build();

        default:
            return Response.ok().entity("Error: wrong section name!").build(); //$NON-NLS-1$
        }
    }

    /* Метод Read */
    @Override
    public Response get(String service, String action, String section, String name, String left, String right,
        String id) {

        switch (section) {
        case "grammemes": //$NON-NLS-1$
            GrammemeProcessor gmp = new GrammemeProcessor();
            return Response.ok().entity(gmp.read(name)).build();

        case "restrictions": //$NON-NLS-1$
            RestrictionProcessor rtp = new RestrictionProcessor();
            return Response.ok().entity(rtp.read(left, right)).build();
        case "lemmata": //$NON-NLS-1$
            LemmaProcessor lmp = new LemmaProcessor();
            return Response.ok().entity(lmp.read(id)).build();

        case "link_types": //$NON-NLS-1$
            LinkTypeProcessor ltp = new LinkTypeProcessor();
            return Response.ok().entity(ltp.read(id)).build();

        case "links": //$NON-NLS-1$
            LinkProcessor lkp = new LinkProcessor();
            return Response.ok().entity(lkp.read(id)).build();

        default:
            return Response.ok().entity("Error: wrong section name!").build(); //$NON-NLS-1$
        }
    }

    /* Метод Update */
    @Override
    public Response put(String service, String action, String section, String name, String left, String right,
        String id, List<String> params) {

        switch (section) {
        case "grammemes": //$NON-NLS-1$
            GrammemeProcessor gmp = new GrammemeProcessor();
            return Response.ok().entity(gmp.update(name, params)).build();

        case "restrictions": //$NON-NLS-1$
            RestrictionProcessor rtp = new RestrictionProcessor();
            return Response.ok().entity(rtp.update(left, right, params)).build();

        case "lemmata": //$NON-NLS-1$
            LemmaProcessor lmp = new LemmaProcessor();
            return Response.ok().entity(lmp.update(id, params)).build();

        case "link_types": //$NON-NLS-1$
            LinkTypeProcessor ltp = new LinkTypeProcessor();
            return Response.ok().entity(ltp.update(id, params)).build();

        case "links": //$NON-NLS-1$
            LinkProcessor lkp = new LinkProcessor();
            return Response.ok().entity(lkp.update(id, params)).build();

        default:
            return Response.ok().entity("Error: wrong section name!").build(); //$NON-NLS-1$
        }
    }

    /* Метод Delete */
    @Override
    public Response delete(String service, String action, String section, String name, String left, String right,
        String id) {

        switch (section) {
        case "grammemes": //$NON-NLS-1$
            GrammemeProcessor gmp = new GrammemeProcessor();
            return Response.ok().entity(gmp.delete(name)).build();

        case "restrictions": //$NON-NLS-1$
            RestrictionProcessor rtp = new RestrictionProcessor();
            return Response.ok().entity(rtp.delete(left, right)).build();

        case "lemmata": //$NON-NLS-1$
            LemmaProcessor lmp = new LemmaProcessor();
            return Response.ok().entity(lmp.delete(id)).build();

        case "link_types": //$NON-NLS-1$
            LinkTypeProcessor ltp = new LinkTypeProcessor();
            return Response.ok().entity(ltp.delete(id)).build();

        case "links": //$NON-NLS-1$
            LinkProcessor lkp = new LinkProcessor();
            return Response.ok().entity(lkp.delete(id)).build();

        default:
            return Response.ok().entity("Error: wrong section name!").build(); //$NON-NLS-1$
        }
    }

    @Activate
    public void activate(ComponentContext context) throws IOException {
        System.out.println("Oomph service started"); //$NON-NLS-1$
    }

    @Deactivate
    public void deactivate(ComponentContext context) {
        System.out.println("Oomph service stopped"); //$NON-NLS-1$
    }

    @Modified
    public void modify() {
        System.out.println("Oomph service modified"); //$NON-NLS-1$
    }

    @Override
    public void updated(Dictionary<String, ?> properties) throws ConfigurationException {
        // Does nothing
    }
}
