package com.hartwig.hmftools.common.ecrf.datamodel;

import java.util.Map;

import org.jetbrains.annotations.NotNull;

public class EcrfField implements Comparable<EcrfField> {

    @NotNull
    private final String studyEventOID;
    @NotNull
    private final String formOID;
    @NotNull
    private final String itemGroupOID;
    @NotNull
    private final String itemOID;
    @NotNull
    private final String description;
    @NotNull
    private final Map<Integer, String> codeList;

    public EcrfField(@NotNull final String studyEventOID, @NotNull final String formOID,
            @NotNull final String itemGroupOID, @NotNull final String itemOID, @NotNull final String description,
            @NotNull final Map<Integer, String> codeList) {
        this.studyEventOID = studyEventOID;
        this.formOID = formOID;
        this.itemGroupOID = itemGroupOID;
        this.itemOID = itemOID;
        this.description = description;
        this.codeList = codeList;
    }

    @NotNull
    public String name() {
        return EcrfFieldFunctions.name(studyEventOID, formOID, itemGroupOID, itemOID);
    }

    @NotNull
    public String description() {
        return description;
    }

    @NotNull
    public Map<Integer, String> codeList() {
        return codeList;
    }

    @NotNull
    String itemOID() {
        return itemOID;
    }

    public int compareTo(@NotNull EcrfField other) {
        return name().compareTo(other.name());
    }
}
