package io.cyborgcode.roa.ui.components.factory;

import io.cyborgcode.roa.ui.components.accordion.Accordion;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.alert.Alert;
import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.Button;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.checkbox.Checkbox;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.factory.mock.FailInputImpl;
import io.cyborgcode.roa.ui.components.factory.mock.MockInputComponentType;
import io.cyborgcode.roa.ui.components.factory.mock.MockInputImpl;
import io.cyborgcode.roa.ui.components.input.Input;
import io.cyborgcode.roa.ui.components.link.Link;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.list.ItemList;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.loader.Loader;
import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.components.modal.Modal;
import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.components.radio.Radio;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.select.Select;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.tab.Tab;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.service.Table;
import io.cyborgcode.roa.ui.components.toggle.Toggle;
import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.config.UiConfig;
import io.cyborgcode.roa.ui.config.UiConfigHolder;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.testutil.BaseUnitUITest;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ComponentFactory Tests")
class ComponentFactoryTest extends BaseUnitUITest {

   private static final String TEST_PACKAGE = "test.package";
   private static final String FRAMEWORK_PACKAGE = "io.cyborgcode.roa";
   private static final String EXPECTED_CREATION_FAILURE_MSG = "Simulated constructor failure!";
   @Mock
   private WebDriver webDriver;
   @Mock
   private UiConfig uiConfig;
   private SmartWebDriver smartWebDriver;
   private MockedStatic<ReflectionUtil> reflectionMock;
   private MockedStatic<UiConfigHolder> uiConfigHolderMock;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
      smartWebDriver = new SmartWebDriver(webDriver);
      reflectionMock = mockStatic(ReflectionUtil.class);
      uiConfigHolderMock = mockStatic(UiConfigHolder.class);

      lenient().when(uiConfig.projectPackages()).thenReturn(new String[] {TEST_PACKAGE});
      when(UiConfigHolder.getUiConfig()).thenReturn(uiConfig);
   }

   @AfterEach
   void tearDown() {
      reflectionMock.close();
      uiConfigHolderMock.close();
   }

   @Test
   @DisplayName("getComponent should return correct instance on success")
   void getComponent_Success_ReturnsCorrectInstance() {
      // Given
      var componentType = MockInputComponentType.DUMMY;
      List<Class<? extends Input>> implList = List.of(MockInputImpl.class);

      reflectionMock.when(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(TEST_PACKAGE)))
         .thenReturn(new ArrayList<>(implList));
      reflectionMock.when(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(FRAMEWORK_PACKAGE)))
         .thenReturn(new ArrayList<>());

      // When
      var result = ComponentFactory.getInputComponent(componentType, smartWebDriver);

      // Then
      assertThat(result).isNotNull();
      assertThat(result).isInstanceOf(MockInputImpl.class);
      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(TEST_PACKAGE)));
      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(FRAMEWORK_PACKAGE)));
   }

   @Test
   @DisplayName("getComponent should throw ComponentNotFoundException when no implementation found")
   void getComponent_NotFound_ThrowsComponentNotFoundException() {
      // Given
      var componentType = MockInputComponentType.NON_EXISTENT;
      reflectionMock.when(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), anyString()))
         .thenReturn(Collections.emptyList());

      // When
      var thrown = catchThrowable(() -> ComponentFactory.getInputComponent(componentType, smartWebDriver));

      // Then
      assertThat(thrown)
         .isInstanceOf(ComponentFactory.ComponentNotFoundException.class)
         .hasMessageContaining("No implementation found for type: " + componentType.name());

      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(TEST_PACKAGE)));
      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(FRAMEWORK_PACKAGE)));
   }

   @Test
   @DisplayName("getComponent should throw ComponentCreationException when instantiation fails")
   void getComponent_CreationFailed_ThrowsComponentCreationException() {
      // Given
      var componentType = MockInputComponentType.FAIL;
      List<Class<? extends Input>> implList = List.of(FailInputImpl.class);

      reflectionMock.when(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(TEST_PACKAGE)))
         .thenReturn(new ArrayList<>(implList));
      reflectionMock.when(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(FRAMEWORK_PACKAGE)))
         .thenReturn(new ArrayList<>());

      // When
      var thrown = catchThrowable(() -> ComponentFactory.getInputComponent(componentType, smartWebDriver));

      // Then
      assertThat(thrown)
         .isInstanceOf(ComponentFactory.ComponentCreationException.class)
         .hasMessageContaining("Failed to create instance of " + FailInputImpl.class.getName());

      assertThat(thrown.getCause())
         .isInstanceOf(InvocationTargetException.class);

      assertThat(thrown.getCause().getCause())
         .isInstanceOf(IllegalStateException.class)
         .hasMessage(EXPECTED_CREATION_FAILURE_MSG);

      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(TEST_PACKAGE)));
      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(Input.class), eq(FRAMEWORK_PACKAGE)));
   }

   @ParameterizedTest
   @MethodSource("provideAllComponentGetters")
   @DisplayName("All component getters should invoke the factory correctly")
   <T> void allComponentGetters_InvokesFactoryCorrectly(
      Class<T> componentClass,
      ComponentType componentType,
      BiFunction<ComponentType, SmartWebDriver, Object> getterMethod
   ) {
      // Given - mock to return empty lists so ComponentNotFoundException is thrown
      reflectionMock.when(() -> ReflectionUtil.findImplementationsOfInterface(eq(componentClass), anyString()))
         .thenReturn(Collections.emptyList());

      // When
      var thrown = catchThrowable(() -> getterMethod.apply(componentType, smartWebDriver));

      // Then - verify the method was called and threw the expected exception
      assertThat(thrown).isInstanceOf(ComponentFactory.ComponentNotFoundException.class);
      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(componentClass), eq(TEST_PACKAGE)));
      reflectionMock.verify(() -> ReflectionUtil.findImplementationsOfInterface(eq(componentClass), eq(FRAMEWORK_PACKAGE)));
   }

   private static Stream<Arguments> provideAllComponentGetters() {
      return Stream.of(
         Arguments.of(Button.class, stubComponentType(ButtonComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getButtonComponent((ButtonComponentType) type, driver)),
         Arguments.of(Radio.class, stubComponentType(RadioComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getRadioComponent((RadioComponentType) type, driver)),
         Arguments.of(Select.class, stubComponentType(SelectComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getSelectComponent((SelectComponentType) type, driver)),
         Arguments.of(ItemList.class, stubComponentType(ItemListComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getListComponent((ItemListComponentType) type, driver)),
         Arguments.of(Loader.class, stubComponentType(LoaderComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getLoaderComponent((LoaderComponentType) type, driver)),
         Arguments.of(Link.class, stubComponentType(LinkComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getLinkComponent((LinkComponentType) type, driver)),
         Arguments.of(Alert.class, stubComponentType(AlertComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getAlertComponent((AlertComponentType) type, driver)),
         Arguments.of(Tab.class, stubComponentType(TabComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getTabComponent((TabComponentType) type, driver)),
         Arguments.of(Checkbox.class, stubComponentType(CheckboxComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getCheckBoxComponent((CheckboxComponentType) type, driver)),
         Arguments.of(Toggle.class, stubComponentType(ToggleComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getToggleComponent((ToggleComponentType) type, driver)),
         Arguments.of(Modal.class, stubComponentType(ModalComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getModalComponent((ModalComponentType) type, driver)),
         Arguments.of(Accordion.class, stubComponentType(AccordionComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getAccordionComponent((AccordionComponentType) type, driver)),
         Arguments.of(Table.class, stubComponentType(TableComponentType.class),
            (BiFunction<ComponentType, SmartWebDriver, Object>) (type, driver) ->
               ComponentFactory.getTableComponent((TableComponentType) type, driver))
      );
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   private static <T extends ComponentType> T stubComponentType(Class<T> componentTypeClass) {
      T mockType = mock(componentTypeClass);
      when(mockType.getType()).thenReturn((Enum) TestComponentEnum.DUMMY);
      return mockType;
   }

   private enum TestComponentEnum {
      DUMMY
   }
}