import java.util.*;

class Heap
{
    Object [][] heap;
    int size;
    int startInt = 0;
    String startStr = null;
    int leftSon = 0;
    String leftStr = null;
    int rigthSon = 0;
    String rightStr = null;

    int indexStart;
    int indexLeftSon;
    int indexRigthSon;

    Heap(int sizeHip)
    {
        heap = new Object[sizeHip][2];
        size = 0;
    }

    public Object[] removeMax() //size--
    {
        Object[] answ = heap[0];
        if (size > 0) {
            heap[0] = heap[size - 1];
            size--;
        }
        siftDown();
        return answ;
    }
    public void addElem(String word, int count) //size++
    {
        size++;
        heap[size - 1] = new Object[]{word,count};
        siftUp();
    }


    public void changePlace(int child)
    {
        Object[] tmp;

        tmp = heap[child];
        heap[child] = heap[indexStart];
        heap[indexStart] = tmp;
        indexStart = child;
        indexLeftSon = getLeftChild(indexStart);
        indexRigthSon = getRightChild(indexStart);
    }

    public boolean differentNums(int start, int first, int second, String startStr, String firstStr)
    {
        return (first > second && ((start < first) || (start == first && startStr.compareTo(firstStr) > 0)));
    }
    public boolean onlySize(int startInt, int find, String startStr, String findStr)
    {
        return  ((startInt < find) || (startInt == find && startStr.compareTo(findStr) > 0));
    }

    public void initParams()
    {
        startInt = (int)heap[indexStart][1];
        startStr = (String)heap[indexStart][0];
        leftSon = 0;
        rigthSon = 0;
        leftStr = null;
        rightStr = null;
        if (indexLeftSon < size)
        {
            leftSon = (int)heap[indexLeftSon][1];
            leftStr = (String)heap[indexLeftSon][0];
        }
        if (indexRigthSon < size)
        {
            rigthSon = (int)heap[indexRigthSon][1];
            rightStr = (String)heap[indexRigthSon][0];
        }
    }

    public void siftDown()
    {
        indexStart = 0;
        indexLeftSon = getLeftChild(indexStart);
        indexRigthSon = getRightChild(indexStart);
        while (true)
        {
            initParams();
            if (indexLeftSon < size && indexRigthSon < size)
            {
                if (differentNums(startInt, leftSon, rigthSon, startStr, leftStr))
                    changePlace(indexLeftSon);
                else if (differentNums(startInt, rigthSon, leftSon, startStr, rightStr))
                    changePlace(indexRigthSon);

                else if(leftSon == rigthSon && (leftSon > startInt)) {
                    if (leftStr.compareTo(rightStr) < 0)
                        changePlace(indexLeftSon);
                    else if (leftStr.compareTo(rightStr) > 0)
                        changePlace(indexRigthSon);
                }
                else if(leftSon == rigthSon && leftSon == startInt) {
                    if (leftStr.compareTo(rightStr) < 0) {
                        if (leftStr.compareTo(startStr) < 0) changePlace(indexLeftSon);
                        else break;
                    }
                    else {
                        if (rightStr.compareTo(startStr) < 0) changePlace(indexRigthSon);
                        else break;
                    }
                }
                else
                    break;
            }
            else if ((indexLeftSon < size) && onlySize(startInt, leftSon, startStr, leftStr))
                changePlace(indexLeftSon);
            else if ((indexRigthSon < size) && onlySize(startInt, rigthSon, startStr, rightStr))
                changePlace(indexRigthSon);
            else
                break;
        }
    }

    public void siftUp()
    {
        int child = size - 1;
        int parent = getParent(child);
        Object[] tmp;
        String one = (String)heap[parent][0];
        while (parent > -1 && ((int)heap[parent][1] < (int)heap[child][1]
                || parent != child && heap[parent][1] == heap[child][1]
                && one.compareTo(heap[child][0].toString()) > 0))
        {
            tmp = heap[child];
            heap[child] = heap[parent];
            heap[parent] = tmp;
            child = parent;
            parent = getParent(child);
            one = (String)heap[parent][0];
        }
    }

    public int getParent(int child) { return (child - 1) / 2; }

    public int getLeftChild(int parent) { return parent * 2 + 1; }

    public int getRightChild(int parent) { return parent * 2 + 2; }
}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> dict = new HashMap<>();
        List<String> answ = new ArrayList<>();
        for (int i = 0; i < words.length; i++)
        {
            if (!dict.containsKey(words[i]))
                dict.put(words[i], 0);
            dict.put(words[i], dict.get(words[i]) + 1);
        }
        Heap heap = new Heap(dict.size());
        for (Map.Entry<String, Integer> entry: dict.entrySet())
            heap.addElem(entry.getKey(), entry.getValue());
        for(int i = 0; i < k; i++)
            answ.add((String)heap.removeMax()[0]);
        return answ;
    }
}
