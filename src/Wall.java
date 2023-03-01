import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure{
    private List<Block> blocks;

    //metoda iteruje przez liste blockow i sprawdza czy kolor odpowiada podanemu parametrowi, zwraca go jako wartosc opcjnalna
    //natomiast jesli block jest typu compositeblock to iteruje po nich i sprawdza czy ktorys z nich ma podany kolor
    @Override
    public Optional<Block> findBlockByColor(String color) {
        for(Block block: blocks){
            if(block.getColor().equals(color)){
                return Optional.of(block);
            } else if (block instanceof CompositeBlock) {
                CompositeBlock compositeBlock = (CompositeBlock) block;
                for(Block blockIn : compositeBlock.getBlocks()){
                    if (blockIn.getColor().equals(color)){
                        return Optional.of(blockIn);
                    }
                }
            }
        }
        return Optional.empty();
    }

    //metoda przeszukuje  bloki w scianie i dodaje do listy matchingBlocks.
    //metoda przeszukuje jego podbloki i dodaje wszystkie pasujace blocki do listy
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> machingBlocks = new ArrayList<>();
        for(Block block: blocks){
            if(block.getMaterial().equals(material)){
                machingBlocks.add(block);
            }
            if (block instanceof CompositeBlock) {
                List<Block> subBlocks = ((CompositeBlock) block).getBlocks();
                for (Block subBlock :subBlocks){
                    if(subBlock.getMaterial().equals(material)){
                        machingBlocks.add(subBlock);
                    }
                }
            }
        }
        return machingBlocks;
    }

    //metoda zlicza bloki w scianie uwzgledniajac bloki zlozone
    @Override
    public int count() {
        int count = blocks.size();
        for (Block block: blocks){
            if(block instanceof CompositeBlock) {
                count+= ((CompositeBlock) block).getBlocks().size() -1;
            }
        }
        return count;
    }
}
