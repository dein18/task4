package task4.property;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap = yaml.load(resource.getInputStream());
        Map<String, Object> flattenedMap = flattenMap(yamlMap);

        String sourceName = name != null ? name : resource.getResource().getFilename();
        return new MapPropertySource(sourceName, flattenedMap);
    }

    private Map<String, Object> flattenMap(Map<String, Object> source) {
        Map<String, Object> result = new LinkedHashMap<>();
        flatten("", source, result);
        return result;
    }

    @SuppressWarnings("unchecked")
    private void flatten(String prefix, Map<String, Object> map, Map<String, Object> result) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
            Object val = entry.getValue();
            if (val instanceof Map) {
                flatten(key, (Map<String, Object>) val, result);
            } else {
                result.put(key, val);
            }
        }
    }
}