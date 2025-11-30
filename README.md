# ME Infinity Cell
- Added Infinity Cobblestone Cell & Infinity Water Cell for AE2 
- Added KubeJS support for registry ME Infinity Cell

### Example
```JavaScript
StartupEvents.registry("item",event =>{
    event.create("infinity_iron_ingot_cell","meinfinitycell:infinity_cell").itemType("minecraft:iron_ingot")
    event.create("infinity_lava_cell","meinfinitycell:infinity_cell").fluidType("minecraft:lava")
    event.create("test","meinfinitycell:infinity_cell").type(AEKeyHelper.item('minecraft:potion','[potion_contents={potion:"minecraft:long_night_vision"}]'))

    //Additional Mod required: Applied Flux
    event.create("infinity_fe_cell","meinfinitycell:infinity_cell").type(FluxKeyHelper.of(EnergyType.FE))
    //Additional Mod required: Applied Mekanistics
    event.create("infinity_oxygen_cell","meinfinitycell:infinity_cell").type(MekanismKeyHelper.of("mekanism:oxygen"))
    event.create("infinity_carbon_cell","meinfinitycell:infinity_cell").type(MekanismKeyHelper.of("mekanism:carbon"))
    event.create("infinity_yellow_cell","meinfinitycell:infinity_cell").type(MekanismKeyHelper.of("mekanism:yellow"))
    event.create("infinity_clean_osmium_cell","meinfinitycell:infinity_cell").type(MekanismKeyHelper.of("mekanism:clean_osmium"))
})

//Of course, you can also use "Java.loadClass()" to obtain an AEKey to register an infinity cell
const $SourceKey = Java.loadClass('gripe._90.arseng.me.key.SourceKey')
StartupEvents.registry("item",event =>{
    //Additional Mod required: Ars Énergistique
    event.create("infinity_source_cell","meinfinitycell:infinity_cell").type(() => $SourceKey.KEY)
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
        keys.add(AEKeyHelper.item('minecraft:enchanted_book','[stored_enchantments={levels:{"minecraft:luck_of_the_sea":1}}]'))
    }))
})
```