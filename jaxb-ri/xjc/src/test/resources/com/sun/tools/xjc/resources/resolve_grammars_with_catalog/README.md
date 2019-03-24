Works:

```
xjc schema.xsd -b schema-relative.xjb
```

Does not work:

```
xjc -catalog catalog.cat http://www.ab.org/schema.xsd 
```

Does not work:

```
xjc -catalog catalog.cat http://www.ab.org/schema.xsd -b schema-absolute.xjb
```

Does not work:

```
xjc -catalog catalog.cat http://www.ab.org/schema.xsd -b schema-relative.xjb
```