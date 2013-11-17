/*******************************************************************************
 * Copyright 2013 Jan Schulte
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.geow.dataprocessing;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DefaultProcessingStrategy<T> implements IProcessingStrategy{

	private static Logger logger = LogManager.getLogger(DefaultProcessingStrategy.class
				.getName());
	private int stepIndex = 0;
	protected IObjectStreamer<T> streamer;
	private IProcessingStep<T> currentStep;
	protected List<IProcessingStep<T>> processingSteps;
	protected Long timestamp;
	protected PropertiesConfiguration config;

	public DefaultProcessingStrategy() {
		super();
		processingSteps = new ArrayList<IProcessingStep<T>>();
	}

	@Override
	public boolean hasStep() {
		return stepIndex < processingSteps.size() ? true : false;
	}

	@Override
	public void finalizeStep() {
		currentStep.finalizeRemaining();
	}

	@Override
	public String nextStep() throws StreamException {
		currentStep = processingSteps.get(stepIndex);
		stepIndex++;
		streamer.restart();
		return currentStep.name();
	}

	@Override
	public boolean hasObject() throws StreamException {
		return streamer.hasNext();
	}

	@Override
	public String nextObject() throws StreamException, ProcessingException {
		return currentStep.processObject(streamer.next());		
	}

	@Override
	public int stepCount() {
		return processingSteps.size();
	}

	@Override
	public int currentStep() {
		return stepIndex;
	}

}