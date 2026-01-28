package io.cyborgcode.roa.framework.quest.mock;

import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.quest.Quest;

public class TestableQuest extends Quest {

   public void exposeRegisterRing(Class<? extends FluentService> ringType, FluentService ring) {
      registerRing(ringType, ring);
   }

   public void exposeRemoveRing(Class<? extends FluentService> ringType) {
      removeRing(ringType);
   }

   public <T extends FluentService> T exposeCast(Class<T> ringType) {
      return cast(ringType);
   }

   public <T extends FluentService, K> K exposeArtifact(Class<T> ringType, Class<K> artifactType) {
      return artifact(ringType, artifactType);
   }
}
