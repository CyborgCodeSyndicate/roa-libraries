package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.JourneyData;
import io.cyborgcode.roa.framework.annotation.PreQuest;

public class MockTest {

   @PreQuest({
         @Journey(value = "mockJourney", journeyData = {@JourneyData(value = "mockData", late = false)}, order = 1)
   })
   public void annotatedMethod() {
   }

   public void nonAnnotatedMethod() {
   }
}
