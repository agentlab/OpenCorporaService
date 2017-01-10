/**
 *
 */
package com.bmstu.coursework.oomph.model;

import java.util.Collection;

/**
 *
 * Instance of this class represents project.
 *
 * @author Kiryakov_E
 *
 */
public class Project {
    private String name;
    private String iconUrl;
    private String profilVendor;
    private String profileMaintainer;
    private String productFile;
    private String productId;
    private Collection<ProjectFile> projectFiles;

    /**
     * Default Constructor
     */
    public Project() {
        // Does nothing
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl the iconUrl to set
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * @return the profilVendor
     */
    public String getProfilVendor() {
        return profilVendor;
    }

    /**
     * @param profilVendor the profilVendor to set
     */
    public void setProfilVendor(String profilVendor) {
        this.profilVendor = profilVendor;
    }

    /**
     * @return the profileMaintainer
     */
    public String getProfileMaintainer() {
        return profileMaintainer;
    }

    /**
     * @param profileMaintainer the profileMaintainer to set
     */
    public void setProfileMaintainer(String profileMaintainer) {
        this.profileMaintainer = profileMaintainer;
    }

    /**
     * @return the productFile
     */
    public String getProductFile() {
        return productFile;
    }

    /**
     * @param productFile the productFile to set
     */
    public void setProductFile(String productFile) {
        this.productFile = productFile;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the projectFiles
     */
    public Collection<ProjectFile> getProjectFiles() {
        return projectFiles;
    }

    /**
     * @param projectFiles the projectFiles to set
     */
    public void setProjectFiles(Collection<ProjectFile> projectFiles) {
        this.projectFiles = projectFiles;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
        {
            return false;
        }
        if (object == this)
        {
            return true;
        }

        return (object instanceof Project && ((Project)object).getName().equals(this.getName()));
    }

    @SuppressWarnings("unused")
    private static class ProjectFile {
        private String projectFile;
        private Collection<String> projectIds;

        public ProjectFile() {
            // Does nothig
        }

        public String getProjectFile() {
            return projectFile;
        }

        public void setProjectFile(String projectFile) {
            this.projectFile = projectFile;
        }

        public Collection<String> getProjectIds() {
            return projectIds;
        }

        public void setProjectIds(Collection<String> projectIds) {
            this.projectIds = projectIds;
        }
    }
}
