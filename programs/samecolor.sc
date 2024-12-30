// samecolor: a scarpet app by AKminer2 to change different colors of concrete, stained glass,
// etc. in a specfied area to all be the same color. This app is intended to be used for redstone
// designing.

global_colors = ['help', 'white', 'orange', 'magenta', 'light_blue', 'yellow', 'lime', 'pink', 'gray', 'light_gray', 'cyan', 'purple', 'blue', 'brown', 'green', 'red', 'black'];
global_block_types = ['placeholder_to_fix_weird_scarpet_problem', 'concrete_powder', 'concrete', 'stained_glass', 'wool', 'glazed_terracotta', 'terracotta'];

//    /samecolor <color> <from> <to>
__config() -> {
    'commands' -> {
        '' -> 'menu',
        '<color>' -> ['samecolor', null, null],
        '<color> <from> <to>' -> 'samecolor'
    },
    'arguments' -> {
        'color' -> {
            'type' -> 'term',
            'options' -> global_colors
        },
        'from' -> {
            'type' -> 'pos'
        },
        'to' -> {
            'type' -> 'pos'
        }
    }
};

blockGroup(block) -> (
    if(str(block) ~ 'concrete_powder', return('concrete_powder'));
    if(str(block) ~ 'concrete', return('concrete'));
    if(str(block) ~ 'stained_glass', return('stained_glass'));
    if(str(block) ~ 'wool', return('wool'));
    if(str(block) ~ 'glazed_terracotta', return('glazed_terracotta'));
    if(str(block) ~ 'terracotta', return('terracotta'));
);

menu() -> (
    print('');
    print('samecolor: a scarpet app by AKminer2 to change different colors of concrete, stained glass, etc. in a specified area to all be the same color. This app is intended to be used for redstone designing purposes.');
    print('For more info, run  /samecolor help');
    print('');
);

help() -> (
        print('');
        print('/samecolor <color> <from> <to>');
        print('');
        print('color: black, white, blue, green, etc.');
        print('from: the first corner of the cuboid in which blocks are affected');
        print('to: the second corner');
        print('');
        print('Blocks affected: concrete, concrete powder, stained glass, wool, terracotta, glazed_terracotta');
        print('Every instance of the above colored blocks will be replaced with a block of the color specified as a parameter to the command.');
        print('');
);

samecolor(color, from, to) -> (
    // if color is == help, then display help message
    if(color == 'help',
        help();
        return();
    );

    // turn fillUpdates to false
    run('carpet fillUpdates false');
    volume(from, to,
        if(global_block_types ~ blockGroup(_),
            set(_, color+'_'+blockGroup(_));
        );
    );
    print('Operation completed');
);