/**
 *
 */
package com.bmstu.coursework.oomph.ds.host;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import com.bmstu.coursework.oomph.IOomphService;
import com.bmstu.coursework.oomph.model.Project;
import com.bmstu.coursework.oomph.model.ProjectName;

/**
 *
 * Oomph component realisation.
 *
 * @author Prihodko
 *
 */
@Component(enabled = true, immediate = true,
    property = { "service.exported.interfaces=*", "service.exported.configs=ecf.jaxrs.jersey.server",
        "ecf.jaxrs.jersey.server.urlContext=http://localhost:8080", "ecf.jaxrs.jersey.server.alias=/oomph",
        "service.pid=com.bmstu.coursework.oomph.ds.host.OomphComponent" })
public class OomphComponent
    implements IOomphService, ManagedService {

    private Collection<Project> projects;

    @Override
    public String put(Project project) {
        if (!projects.contains(project))
        {
            if (projects.add(project))
            {
                return "Added"; //$NON-NLS-1$
            }
            else
            {
                return "Not added. Error."; //$NON-NLS-1$
            }
        }
        else
        {
            return "Not added. Already exists"; //$NON-NLS-1$
        }
    }

    @Override
    public Collection<String> get() {
        Collection<String> projectNames = getProjectNames();

        return projectNames;
    }

    @Override
    public Project get(ProjectName projectName) {
        for (Project project : projects)
        {
            if (project.getName().equals(projectName.getProjectName()))
            {
                return project;
            }
        }

        return null;
    }

    @Activate
    public void activate(ComponentContext context) throws IOException {
        projects = new ArrayList<>();
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

    private Collection<String> getProjectNames() {
        Collection<String> projectNames = new ArrayList<>();
        projects.parallelStream().forEach((project) -> {
            projectNames.add(project.getName());
        });
        return projectNames;
    }

}
