/*
 * Copyright 2015
 * Ubiquitous Knowledge Processing (UKP) Lab and FG Language Technology
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tudarmstadt.ukp.clarin.webanno.api.annotation.adapter;

import java.util.List;

import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.clarin.webanno.api.annotation.model.AnnotatorState;
import de.tudarmstadt.ukp.clarin.webanno.model.AnnotationFeature;
import de.tudarmstadt.ukp.clarin.webanno.model.SourceDocument;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

public interface AutomationTypeAdapter
{
    // value NILL for a token when the training file do not have annotations provided
    final static String NILL = "__nill__";
    
    List<String> getAnnotation(Sentence aSentence, AnnotationFeature feature);

    /**
     * Delete based on the begin,end, and type of annotation.
     */
    void delete(SourceDocument aDocument, String aUsername, JCas aJCas, AnnotationFeature feature,
            int aBegin, int aEnd, Object aValue);

    /**
     * @deprecated The UI class {@link AnnotatorState} should not be passed here. Use
     *    {@link #delete(SourceDocument, String, JCas, AnnotationFeature, int, int, Object)}
     *    instead.
     */
    @Deprecated
    default void delete(AnnotatorState aState, JCas aJCas, AnnotationFeature aFeature, int aBegin,
            int aEnd, Object aValue)
    {
        delete(aState.getDocument(), aState.getUser().getUsername(), aJCas, aFeature, aBegin, aEnd,
                aValue);
    }
}
