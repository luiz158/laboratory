package org.cruxframework.crux.core.client;

public class ClientMessages_ implements org.cruxframework.crux.core.client.ClientMessages {
  
  public java.lang.String eventProcessorClientControllerNotFound(java.lang.String arg0) {
    return "Client Controller Named '" + arg0 + "' not found.";
  }
  
  public java.lang.String eventProcessorClientError(java.lang.String arg0,java.lang.String arg1) {
    return "Error running client method '" + arg0 + "'. Message: '" + arg1 + "'";
  }
  
  public java.lang.String eventFactoryInvalidHandlerMethodDeclaration(java.lang.String arg0) {
    return "Invalid declaration for handler method '" + arg0 + "'. Correct syntaxe is <controller.method>.";
  }
  
  public java.lang.String viewFactoryCanNotBeLoaded(java.lang.String arg0) {
    return "Error loading screen fragment " + arg0 + ".";
  }
  
  public java.lang.String methodIsAlreadyBeingProcessed() {
    return "Please wait. Your request is still being processed.";
  }
  
  public java.lang.String crossDocumentInvalidCrossDocumentController(java.lang.String arg0) {
    return "Client Controller Named '" + arg0 + "' does not implement CrossDocument interface and can not be called outside document.";
  }
  
  public java.lang.String crossDocumentInvocationError() {
    return "Error on cross document call. No responde received from method.";
  }
  
  public java.lang.String crossDocumentInvalidTarget() {
    return "Error on cross document call. Invalid Target.";
  }
  
  public java.lang.String crossDocumentInvocationGeneralError(java.lang.String arg0,java.lang.String arg1) {
    return "Error on cross document call: Screen [" + arg0 + "]. Error Message: [" + arg1 + "].";
  }
  
  public java.lang.String crossDocumentCanNotIdentifyMethod() {
    return "Can not identify the method to be called.";
  }
  
  public java.lang.String crossDocumentMethodNotFound() {
    return "Can not find the method to be called.";
  }
  
  public java.lang.String crossDocumentSerializationErrorStreamClosed() {
    return "WriterStream is not open.";
  }
  
  public java.lang.String screenFactoryLayoutPanelWithoutSize(java.lang.String arg0) {
    return "The widget '" + arg0 + "' is a layout panel that does not have its dimensions defined. Explicity define it, or append it directly on body element.";
  }
  
  public java.lang.String screenFactoryScreenIdRequired() {
    return "The id attribute is required for CRUX Screens.";
  }
  
  public java.lang.String viewContainerErrorCreatingView(java.lang.String arg0) {
    return "Error creating view [" + arg0 + "]. ";
  }
  
  public java.lang.String asyncCallbackInvalidHandlerError() {
    return "An Invalid controller was passed to AsyncCallbackAdapter.";
  }
  
  public java.lang.String screenInvalidObjectError() {
    return "An Invalid object was passed to update screen or DTOs.";
  }
  
  public java.lang.String viewContainerCreatingView(java.lang.String arg0) {
    return "Creating the view for screen " + arg0 + ".";
  }
  
  public java.lang.String viewContainerViewCreated(java.lang.String arg0) {
    return "View " + arg0 + " created.";
  }
  
  public java.lang.String viewContainerViewRendered(java.lang.String arg0) {
    return "View " + arg0 + " rendered.";
  }
  
  public java.lang.String viewContainerUnsupportedWidget() {
    return "This application contains components that are not fully supported by your brownser.";
  }
  
  public java.lang.String screenFactoryCrux2OldInterfacesCompatibilityDisabled() {
    return "To use this feature you must enabled compatibility with Crux 2 old interfaces.";
  }
  
  public java.lang.String moduleComunicationInvalidParamType(java.lang.String arg0) {
    return "Type '" + arg0 + "' can not be shared between modules. Only primitives (and its wrappers), Strings, Dates, Arrays (not multidimesional) and classes implementing CruxSerializable can be used.";
  }
  
  public java.lang.String localDataSourceErrorLoadingData(java.lang.String arg0) {
    return "Error loading dataSource data: " + arg0;
  }
  
  public java.lang.String remoteDataSourceErrorLoadingData(java.lang.String arg0) {
    return "Error loading dataSource remote data: " + arg0;
  }
  
  public java.lang.String dataSourceNotLoaded() {
    return "Error processing requested operation. DataSource is not loaded yet.";
  }
  
  public java.lang.String remoteDataSourcePageDirty() {
    return "DataSource has changes on page. You must save or discard them before perform this operation.";
  }
  
  public java.lang.String dataSourceErrorColumnNotComparable(java.lang.String arg0) {
    return "The column " + arg0 + " can not be sorted.";
  }
  
  public java.lang.String nullElementAtSetStyleName() {
    return "Found a null element reference when trying to modify it's styleName property.";
  }
  
  public java.lang.String emptyStringAsStyleNameValue() {
    return "Empty strings can not be used as a styleName property value.";
  }
  
  public java.lang.String cruxAlreadyInitializedError() {
    return "Crux Engine is already loaded.";
  }
  
  public java.lang.String screenAccessorCallingCrossDocument(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2,java.lang.String arg3) {
    return "Calling a cross document method. Screen[" + arg0 + "], Controller[" + arg1 + "], Method[" + arg2 + "], Target[" + arg3 + "]";
  }
  
  public java.lang.String screenAccessorCrossDocumentExecuted(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2,java.lang.String arg3) {
    return "Cross document method executed. Screen[" + arg0 + "], Controller[" + arg1 + "], Method[" + arg2 + "], Target[" + arg3 + "]";
  }
  
  public java.lang.String styleErrorInvalidProperty(java.lang.String arg0,java.lang.String arg1) {
    return "Invalid value for style property: [" + arg0 + "]:[" + arg1 + "]";
  }
  
  public java.lang.String viewNotInitialized(java.lang.String arg0,java.lang.String arg1) {
    return "Can not retrieve the widget [" + arg1 + "] from view [" + arg0 + "]. View is not loaded. Load this view into a ViewContainer first.";
  }
  
  public java.lang.String viewOjectIsNotAwareOfView() {
    return "Informed object is not aware of current view. This method must be used to discover current view for controllers, datasouces or other ViewAware objects.";
  }
  
  public java.lang.String resourcesInitialized(java.lang.String arg0) {
    return "Resources [" + arg0 + "] initialized.";
  }
  
  public java.lang.String resourceCsssInjected(java.lang.String arg0) {
    return "Css Resource [" + arg0 + "] injected.";
  }
  
  public java.lang.String restServiceUnexpectedError(java.lang.String arg0) {
    return "Unexpected error calling rest service. Error [" + arg0 + "].";
  }
  
  public java.lang.String restServiceMissingStateEtag(java.lang.String arg0) {
    return "Can not invoke write operation for uri[" + arg0 + "] without previously loading it.";
  }
}
