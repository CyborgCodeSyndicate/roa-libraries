package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.components.modal.ModalService;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

public class MockModalService implements ModalService {

   public ModalComponentType lastComponentType;
   public SmartWebElement lastContainer;
   public String lastButtonText;
   public By lastButtonLocator;
   public boolean returnOpened;
   public String returnTitle;
   public String returnBodyText;
   public String returnContentTitle;

   public void reset() {
      lastComponentType = null;
      lastContainer = null;
      lastButtonText = null;
      lastButtonLocator = null;
      returnOpened = false;
      returnTitle = "";
      returnBodyText = "";
      returnContentTitle = "";
   }

   @Override
   public boolean isOpened(ModalComponentType componentType) {
      lastComponentType = componentType;
      return returnOpened;
   }

   @Override
   public void clickButton(ModalComponentType componentType, SmartWebElement container, String modalButtonText) {
      lastComponentType = componentType;
      lastContainer = container;
      lastButtonText = modalButtonText;
   }

   @Override
   public void clickButton(ModalComponentType componentType, String modalButtonText) {
      lastComponentType = componentType;
      lastButtonText = modalButtonText;
   }

   @Override
   public void clickButton(ModalComponentType componentType, By modalButtonLocator) {
      lastComponentType = componentType;
      lastButtonLocator = modalButtonLocator;
   }

   @Override
   public String getTitle(ModalComponentType componentType) {
      lastComponentType = componentType;
      return returnTitle;
   }

   @Override
   public String getBodyText(ModalComponentType componentType) {
      lastComponentType = componentType;
      return returnBodyText;
   }

   @Override
   public String getContentTitle(ModalComponentType componentType) {
      lastComponentType = componentType;
      return returnContentTitle;
   }

   @Override
   public void close(ModalComponentType componentType) {
      lastComponentType = componentType;
   }
}