package io.github.bzdgn.sru.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = FileMeta.TABLE_NAME)
public class FileMeta {

    public static final String TABLE_NAME = "DOCUMENTS";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String identifier;
    private String title;
    private String preferredUrl;
    private String pdfUrl;
    private String metaData;

    public FileMeta() {}

    public FileMeta(String identifier, String title, String preferredUrl, String pdfUrl) {
        this.identifier = identifier;
        this.title = title;
        this.preferredUrl = preferredUrl;
        this.pdfUrl = pdfUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreferredUrl() {
        return preferredUrl;
    }

    public void setPreferredUrl(String preferredUrl) {
        this.preferredUrl = preferredUrl;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getTitle(), getPreferredUrl(), getPdfUrl(), getMetaData());
    }

    @Override
    public String toString() {
        return "FileMeta{" +
                "identifier='" + identifier + '\'' +
                ", title='" + title + '\'' +
                ", preferredUrl='" + preferredUrl + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                ", metaData=" + '\'' + (metaData == null ? "empty" : "filled") + '\'' +
                '}';
    }
}
