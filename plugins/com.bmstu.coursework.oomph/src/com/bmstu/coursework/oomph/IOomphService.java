/**
 *
 */
package com.bmstu.coursework.oomph;

import java.util.Collection;

import com.bmstu.coursework.oomph.model.Project;
import com.bmstu.coursework.oomph.model.ProjectName;

/**
 *
 * Oomph service. Stores projects given by {@link #put(Project)} method.
 * <br>
 * To get project user should use {@link #get(String)} method.
 *
 * @author Prihodko
 *
 */
public interface IOomphService {

    /**
     *
     * Put project for storing.
     *
     * @param project - project to put. Can't be <code>null</code>.
     *
     * @return message to user. (Added or not). Can't return <code>null</code>.
     */
    String put(Project project);

    /**
     *
     * Returns collection with stored project names.
     *
     * @return collection with stored project names. Can't be <code>null</code>.
     */
    Collection<String> get();

    /**
     *
     * Returns project with given name. If there is no project with such name, returns <code>null</code>.
     *
     * @param projectName - project name. Can't be <code>null</code>.
     *
     * @return project with given name. Can return <code>null</code>.
     */
    Project get(ProjectName projectName);

    /**
    *
    * Removes project with given name. If there is no project with such name, returns <code>null</code>.
    *
    * @param projectName - project name. Can't be <code>null</code>.
    *
    * @return message to user. (Deleted or not). Can't return <code>null</code>.
    */
    String delete(ProjectName projectName);
}
