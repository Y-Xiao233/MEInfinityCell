# ME Infinity Cell
- Added Infinity Cobblestone Cell & Infinity Water Cell for AE2
- Added KubeJS support for registry ME Infinity Cell

### Example
```JavaScript
StartupEvents.registry("item",event =>{
    event.create("infinity_iron_ingot_cell","meinfinitycell:infinity_cell").itemType("minecraft:iron_ingot")
    event.create("infinity_lava_cell","meinfinitycell:infinity_cell").fluidType("minecraft:lava")
    event.create("test","meinfinitycell:infinity_cell").type(AEKeyHelper.ofItem(() =>{
        Item.of('minecraft:potion','[potion_contents={potion:"minecraft:long_night_vision"}]')
    }))
})

//In addition to adding infinity cell for one item, you can also add infinities cell for multiple items
StartupEvents.registry("item",event =>{
    event.create("infinities_cell","meinfinitycell:infinities_cell").setName(Text.literal("test")).setKeys(KeyList.create().adds(keys =>{
        keys.add(AEKeyHelper.item("minecraft:diamond"))
        keys.add(AEKeyHelper.item("minecraft:iron_ingot"))
        keys.add(AEKeyHelper.item("minecraft:gold_ingot"))
        keys.add(AEKeyHelper.item("minecraft:copper_ingot"))
        keys.add(AEKeyHelper.item("minecraft:stone"))
        keys.add(AEKeyHelper.fluid("minecraft:water"))
        keys.add(AEKeyHelper.ofItem(
            () => Item.of('minecraft:enchanted_book', '[stored_enchantments={"minecraft:punch":1}]')
        ))
    }))
})
```