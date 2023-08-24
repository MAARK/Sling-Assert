package com.miras.slingassertj.api;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.description.Description.mostRelevantDescription;
import static org.assertj.core.extractor.Extractors.extractedDescriptionOf;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.ObjectAssert;

public abstract class AbstractResourceAssert<T extends AbstractResourceAssert<T>> extends AbstractAssert<T, Resource> {

    protected AbstractResourceAssert(Resource resource, Class<?> selfType) {
        super(resource, selfType);
    }

    T hasResourceType(String resourceType) {
        isNotNull();
        if (!actual.isResourceType(resourceType)) {
            failWithMessage("Expected resource type to be <%s> but was <%s>", resourceType, actual.getResourceType());
        }
        return myself;
    }

    T hasResourceSuperType(String expectedSuperType) {
        isNotNull();
        if (!actual.getResourceSuperType().equals(expectedSuperType)) {
            failWithMessage("Expected resource super type to be <%s> but was <%s>", expectedSuperType, actual.getResourceSuperType());
        }
        return myself;
    }

    T hasResourcePath(String expectedPath) {
        isNotNull();
        if (!actual.getPath().equals(expectedPath)) {
            failWithMessage("Expected resource path to be <%s> but was <%s>", expectedPath, actual.getPath());
        }
        return myself;
    }

    T hasResourceName(String expectedName) {
        isNotNull();
        if (!actual.getName().equals(expectedName)) {
            failWithMessage("Expected resource name to be <%s> but was <%s>", expectedName, actual.getName());
        }
        return myself;
    }

    T hasChildren() {
        isNotNull();
        if (!actual.hasChildren()) {
            failWithMessage("Expected resource to have children but it has none");
        }
        return myself;
    }

    T hasProperty(String propertyName) {
        isNotNull();
        if (!actual.getValueMap().containsKey(propertyName)) {
            failWithMessage("Expected resource to have property <%s> but it has none", propertyName);
        }
        return myself;
    }

    T propertyEquals(String propertyName, Object expectedValue) {
        isNotNull();
        if (!actual.getValueMap().get(propertyName).equals(expectedValue)) {
            failWithMessage("Expected resource property <%s> to be <%s> but was <%s>", propertyName, expectedValue, actual.getValueMap().get(propertyName));
        }
        return myself;
    }

    T hasChildResource(String childResourcePath) {
        isNotNull();
        if (actual.getChild(childResourcePath) == null) {
            failWithMessage("Expected resource to have child resource <%s> but it has none", childResourcePath);
        }
        return myself;
    }

    T childResourceSatisfies(String childResourcePath, Consumer<Resource> assertion) {
        isNotNull();
        Resource childResource = actual.getChild(childResourcePath);
        if (childResource == null) {
            failWithMessage("Expected resource to have child resource <%s> but it has none", childResourcePath);
        }
        assertion.accept(childResource);
        return myself;
    }

    T hasPropertyValue(String propertyName, Object expectedValue) {
        isNotNull();
        if (!actual.getValueMap().get(propertyName).equals(expectedValue)) {
            failWithMessage("Expected resource property <%s> to be <%s> but was <%s>", propertyName, expectedValue, actual.getValueMap().get(propertyName));
        }
        return myself;
    }

    <PT> T propertyValueSatisfies(String propertyName, Class<PT> clazz, Consumer<PT> assertion) {
        isNotNull();
        PT propertyValue = actual.getValueMap().get(propertyName, clazz);
        assertion.accept(propertyValue);
        return myself;
    }

    <AT> T canAdaptTo(Class<AT> type) {
        isNotNull();
        if (actual.adaptTo(type) == null) {
            failWithMessage("Expected resource to be adaptable to <%s> but it is not", type);
        }
        return myself;
    }

    T exists() {
        isNotNull();
        if (actual.getResourceResolver().getResource(actual.getPath()) == null) {
            failWithMessage("Expected resource to exist but it does not");
        }
        return myself;
    }

    AbstractListAssert<?, List<?>, Object, ObjectAssert<Object>> extracting(Object... keys) {
        isNotNull();
        ValueMap map = actual.getValueMap();
        List<Object> extractedValues = Stream.of(keys).map(map::get).collect(toList());
        String extractedPropertiesOrFieldsDescription = extractedDescriptionOf(keys);
        String description = mostRelevantDescription(info.description(), extractedPropertiesOrFieldsDescription);
        return newListAssertInstance(extractedValues).as(description);
    }

}
