/*
 * Copyright 2016-2017 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.eva.commons.mongodb.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import uk.ac.ebi.eva.commons.core.models.Aggregation;
import uk.ac.ebi.eva.commons.core.models.IVariantGlobalStats;
import uk.ac.ebi.eva.commons.core.models.IVariantSource;
import uk.ac.ebi.eva.commons.core.models.StudyType;
import uk.ac.ebi.eva.commons.core.models.stats.VariantGlobalStats;
import uk.ac.ebi.eva.commons.mongodb.entities.subdocuments.VariantGlobalStatsMongo;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapped class for variant source collection in mongo
 */
@Document(collection = "#{mongoCollectionsFiles}")
public class VariantSourceMongo implements IVariantSource {

    public final static String FILEID_FIELD = "fid";

    public final static String FILENAME_FIELD = "fname";

    public final static String STUDYID_FIELD = "sid";

    public final static String STUDYNAME_FIELD = "sname";

    public final static String STUDYTYPE_FIELD = "stype";

    public final static String AGGREGATION_FIELD = "aggregation";

    public final static String DATE_FIELD = "date";

    public final static String SAMPLES_FIELD = "samp";

    public final static String STATISTICS_FIELD = "st";

    public final static String METADATA_FIELD = "meta";

    @Field(value = FILEID_FIELD)
    private String fileId;

    @Field(value = FILENAME_FIELD)
    private String fileName;

    @Field(value = STUDYID_FIELD)
    private String studyId;

    @Field(value = STUDYNAME_FIELD)
    private String studyName;

    @Field(value = STUDYTYPE_FIELD)
    private StudyType type;

    @Field(value = AGGREGATION_FIELD)
    private Aggregation aggregation;

    @Field(value = DATE_FIELD)
    private Date date;

    @Field(value = SAMPLES_FIELD)
    private Map<String, Integer> samplesPosition;

    @Field(value = METADATA_FIELD)
    private Map<String, Object> metadata;

    @Field(value = STATISTICS_FIELD)
    private VariantGlobalStatsMongo stats;

    VariantSourceMongo() {
        //Empty spring constructor
        this(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public VariantSourceMongo(IVariantSource variantSource) {
        this(variantSource.getFileId(), variantSource.getFileName(),
                variantSource.getStudyId(), variantSource.getStudyName(), variantSource.getType(),
                variantSource.getAggregation(), variantSource.getSamplesPosition(), variantSource.getMetadata(),
                new VariantGlobalStatsMongo(variantSource.getStats()));
    }

    public VariantSourceMongo(String fileId, String fileName, String studyId, String studyName,
                              StudyType type, Aggregation aggregation,
                              Map<String, Integer> samplesPosition, Map<String, Object> metadata,
                              VariantGlobalStatsMongo stats) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.studyId = studyId;
        this.studyName = studyName;
        this.type = type;
        this.aggregation = aggregation;
        this.samplesPosition = new HashMap<>();
        if (samplesPosition != null && !samplesPosition.isEmpty()) {
            this.samplesPosition.putAll(samplesPosition);
        }
        this.metadata = new HashMap<>();
        if (metadata != null && !metadata.isEmpty()) {
            this.metadata.putAll(metadata);
        }
        this.stats = stats;
        this.date = Calendar.getInstance().getTime();
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStudyId() {
        return studyId;
    }

    public String getStudyName() {
        return studyName;
    }

    public StudyType getType() {
        return type;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Integer> getSamplesPosition() {
        return samplesPosition;
    }

    public void setSamplesPosition(Map<String, Integer> samplesPosition) {
        this.samplesPosition = samplesPosition;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public VariantGlobalStatsMongo getStats() {
        return stats;
    }

    public void setStats(VariantGlobalStatsMongo stats) {
        this.stats = stats;
    }

}
