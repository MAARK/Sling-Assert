# AssertJ for Sling

Implemented to speed up testing of Sling applications.

## Usage

1. Asserting Resource Properties:

#### Assert that the resource has the specified resource type.
`hasResourceType(String expectedResourceType)`
        
    assertThat(resource)
        .hasResourceType("sling:Folder");

#### Assert that the resource has the specified resource super type.
`hasResourceSuperType(String expectedSuperType)`

    assertThat(resource)
       .hasResourceSuperType("sling:Folder");

####  Assert that the resource has the specified resource path.
`hasResourcePath(String expectedPath)`
            
    assertThat(resource)
        .hasResourcePath("/content/sling/en");

####  Assert that the resource has the specified name.
`hasResourceName(String expectedName)`
                
    assertThat(resource)
        .hasResourceName("jcr:content"); 


2. Child Resource Assertions:

#### Assert that the resource has children.
`hasChildren()`

        assertThat(resource)
            .hasChildren();

#### Assert that the resource has a specific child resource.
`hasChildResource(String childResourcePath)`

    assertThat(resource)
        .hasChildResource("jcr:content");           

#### Asserts properties or conditions on a child resource.
`childResourceSatisfies(String childResourcePath, Consumer<Resource> assertion)`

    assertThat(resource)
        .childResourceSatisfies(
            "jcr:content", 
            content -> assertThat(content).hasProperty("jcr:title"));    

3. Resource Property Assertions:

#### Assert that the resource has a specific property.
`hasProperty(String propertyName)`
            
    assertThat(resource)
        .hasProperty("jcr:title");

4. Resource Value Assertions (for ValueMap):

#### Assert that a property in the resource's ValueMap has a specific value.
`hasPropertyValue(String propertyName, Object expectedValue)`

    assertThat(resource)
        .hasPropertyValue("jcr:title", "Sling");

#### Assert that a property in the ValueMap matches a given predicate.
`propertyValueSatisfies(String propertyName, Class<PT> clazz, Consumer<PT> assertion)`

    assertThat(resource)
        .propertyValueSatisfies(
            "jcr:title", 
            String.class, 
            title -> assertThat(title).isEqualTo("Title"));

#### Extracting property values
`extracting(Object... keys)`

    assertThat(resource)
        .extracting("jcr:title", "jcr:description")
        .containsExactly("Title", "Description");


5. Resource Adaptation Assertions:

#### Assert that the resource can be adapted to a specific type.
`canAdaptTo(Class<T> type)`
            
    assertThat(resource)
        .canAdaptTo(TitleModel.class);    

6. Resource Existence Assertions:

#### Assert that the resource exists.
`exists()`
    
    assertThat(resource)
        .exists();
