package io.cyborgcode.roa.framework.quest.mock;

import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.quest.Quest;

public class TestableQuest extends Quest {

   public void exposeRegisterWorld(Class<? extends FluentService> worldType, FluentService world) {
      registerWorld(worldType, world);
   }

   public void exposeRemoveWorld(Class<? extends FluentService> worldType) {
      removeWorld(worldType);
   }

   public <T extends FluentService> T exposeCast(Class<T> worldType) {
      return cast(worldType);
   }

   public <T extends FluentService, K> K exposeArtifact(Class<T> worldType, Class<K> artifactType) {
      return artifact(worldType, artifactType);
   }
}
