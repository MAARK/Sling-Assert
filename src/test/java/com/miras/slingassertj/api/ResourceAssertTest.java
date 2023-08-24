package com.miras.slingassertj.api;

import static com.miras.slingassertj.api.SlingAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.jupiter.api.Test;

class ResourceAssertTest {

    @Test
    void test() {
        Resource r = mock(Resource.class);
        when(r.isResourceType(anyString())).thenReturn(true);

        assertThat(r).hasResourceType("testrt");
        verify(r).isResourceType("testrt");

        assertThrows(AssertionError.class, () -> {
            when(r.isResourceType(anyString())).thenReturn(false);
            assertThat(r).hasResourceType("testrt");
        });

    }

    @Test
    void extracting() {
        Resource r = mock(Resource.class);
        ValueMap v = new ValueMapDecorator(Map.of("a", "xxx", "b", "yyy"));

        when(r.getValueMap()).thenReturn(v);

        assertThat(r).extracting("a", "b")
                .containsExactly("xxx", "yyy");

    }


}
