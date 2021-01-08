package com.lecture19.Graph;

import com.lecture16.heap.HeapGeneric;

import javax.swing.plaf.DimensionUIResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    private class Vertex {
        HashMap<String, Integer> nbrs = new HashMap<>();

    }

    private class Pair{
        String vname;
        String psf;
    }

    HashMap<String,Vertex> vtces;

    public Graph(){
        vtces = new HashMap<>();
    }

    // 1. Calculate number of vertex
    public int numVertex(){
        return this.vtces.size();
    }

    // 2. Contains vertex
    public boolean containsVertex(String vname){
        return this.vtces.containsKey(vname);
    }

    // 3. Add a vertex
    public void addVertex(String vname){
        Vertex vtx = new Vertex();
        vtces.put(vname, vtx);
    }

    // 4. Remove a vertex
    public void removeVertex(String vname){
        Vertex vtx = vtces.get(vname);
        ArrayList<String> keys = new ArrayList<>(vtx.nbrs.keySet());

        for(String key: keys){

            Vertex nbrVtx = vtces.get(key);
            nbrVtx.nbrs.remove(vname);
        }

        vtces.remove(vname);
    }

    // 5. Number of Edges
    public int numEdges(){
        int count =0;
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());
        for(String key: keys){
            Vertex vtx = vtces.get(key);
            count = count + vtx.nbrs.size();
        }
        return count/2;
    }

    // 6. Contains the edge
    public boolean containsEdge(String vname1, String vname2){
        Vertex vtx1 = vtces.get(vname1);
        Vertex vtx2 = vtces.get(vname2);

        if(vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2)){
            return false;
        }

        return true;
    }

    // 7. Add an edge
    public void addEdge(String vname1, String vname2, int cost){
        Vertex vtx1 = vtces.get(vname1);        //2k
        Vertex vtx2 = vtces.get(vname2);        //4k

        if(vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2)){
            return;
        }

        vtx1.nbrs.put(vname2,cost);     // 2k nbrs put C with a given cost
        vtx2.nbrs.put(vname1,cost);     // 4k nbrs put A with a give cost
    }

    // 8. Remove an edge
     public void removeEdge(String vname1, String vname2){
        Vertex vtx1 = vtces.get(vname1);
        Vertex vtx2 = vtces.get(vname2);

        if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)){
            return;
        }

        vtx1.nbrs.remove(vname2);  // 2k nbrs remove C
        vtx2.nbrs.remove(vname1);   // 4k nbrs remove A
    }

    // 9. has path between two vertex. with the help of recursion we would be resolving things.
    public boolean hasPath(String vname1, String vname2, HashMap<String,Boolean> processed)
    {
        processed.put(vname1,true);

        //direct edge
        if(containsEdge(vname1,vname2)){
            return true;
        }

        // devote the work to nghbrs
        Vertex vtx = vtces.get(vname1);

        ArrayList<String> nbrs = new ArrayList<>(vtx.nbrs.keySet());

        for(String nbr : nbrs){
            if(!processed.containsKey(nbr) && hasPath(nbr, vname2,processed)){
             return true;
            }
        }

        return false;
    }

    // 10. Display
    public void display(){
        System.out.println("-------------------------");
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());
        for(String key: keys){

            Vertex vtx = vtces.get(key);
            System.out.println(key + " : "+vtx.nbrs);
        }

        System.out.println("-------------------------");
    }

    //11. BreadthFirstSearch BFS
    public boolean bfs(String src, String dst){
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();
        Pair sp = new Pair();
        sp.vname = src;
        sp.psf = src;

        // put the new pair in queue
        queue.addLast(sp);

        //while queue is not empty, keep on doing the work
        while(!queue.isEmpty()){
            // remove a pair from queue
            Pair rp = queue.removeFirst();
            if(processed.containsKey(rp.vname)){
                continue;
            }
            // Processed put
            processed.put(rp.vname,true);
            //de
            if(containsEdge(rp.vname, dst)){
                return true;
            }
            // nbrs
            Vertex rpvtx = vtces.get(rp.vname);
            ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

            for(String nbr: nbrs){
                // process only unprocessed neighbour
                if(!processed.containsKey(nbr)){
                    // make a new pair of nbr and put in the queue
                    Pair np = new Pair();
                    np.vname = nbr;
                    np.psf = rp.psf + nbr;
                    queue.addLast(np);
                }
            }
        }

        return false;
    }

    //12. Depth First Search DFS
    public boolean dfs(String src, String dst){
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> stack = new LinkedList<>();

        //create a new pair
        Pair sp = new Pair();
        sp.vname = src;
        sp.psf = src;

        // put the new pair inside the stack
        stack.addFirst(sp);

        //while the stack is not empty keep on doing the work
        while(!stack.isEmpty()){
            // remove a pair from queue
            Pair rp = stack.removeFirst();
            if(processed.containsKey(rp.vname)){
                continue;
            }

            // processed put
            processed.put(rp.vname, true);

            // de
            if(containsEdge(rp.vname, dst)){
                System.out.println(rp.psf +" :: "+dst);
                return true;
            }

            //nbrs
            Vertex rpvtx = vtces.get(rp.vname);
            ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

            // loop on nbrs
            for(String nbr: nbrs){
                // process only unprocessed nbrs
                if(!processed.containsKey(nbrs)){
                    // make a new pair of nbr and put in queue
                    Pair np = new Pair();
                    np.vname = nbr;
                    np.psf = rp.psf + nbr;
                    stack.addFirst(np);
                }
            }
        }
        return false;
    }

    //13. BreadthFirstTraversal BFT
    public void bft(){
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        for(String key:keys){
            if(processed.containsKey(key)){
                continue;
            }

            //create pair
            Pair sp = new Pair();
            sp.vname = key;
            sp.psf = key;

            // put the new pair in queue
            queue.addLast(sp);

            // while queue is not empty keep on doing the work
            while(!queue.isEmpty()){
                // remove a pair from queue
                Pair rp = queue.removeFirst();
                if(processed.containsKey(rp.vname)){
                    continue;
                }

                //processed put
                processed.put(rp.vname,true);

                // de
                System.out.println(rp.vname+ " :: "+rp.psf);

                // nbrs
                Vertex rpvtx = vtces.get(rp.vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // loop on nbrs
                for(String nbr: nbrs){
                    // process only unprocessed nbrs
                    if(!processed.containsKey(nbr)){
                        // make a new pair of nbr and put in the queue
                        Pair np = new Pair();
                        np.vname = nbr;
                        np.psf = rp.vname+nbr;

                        queue.addLast(np);
                    }
                }
            }
        }
    }

    //14. DepthFirstTraversal DFT
    public void dft(){
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> stack = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        for(String key:keys){
            if(processed.containsKey(key)){
                continue;
            }

            //create pair
            Pair sp = new Pair();
            sp.vname = key;
            sp.psf = key;

            // put the new pair in stack
            stack.addFirst(sp);

            // while stack is not empty keep on doing the work
            while(!stack.isEmpty()){
                // remove a pair from stack
                Pair rp = stack.removeFirst();
                if(processed.containsKey(rp.vname)){
                    continue;
                }

                // processed put
                processed.put(rp.vname,true);

                // de
                System.out.println(rp.vname+ " :: "+rp.psf);

                // nbrs
                Vertex rpvtx = vtces.get(rp.vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // loop on nbrs
                for(String nbr: nbrs){
                    // process only unprocessed nbrs
                    if(!processed.containsKey(nbr)){
                        // make a new pair of nbr and put in the stack
                        Pair np = new Pair();
                        np.vname = nbr;
                        np.psf = rp.vname+nbr;

                        stack.addFirst(np);
                    }
                }
            }
        }
    }

    //15. If the graph is cyclic or not
    public boolean isCyclic(){
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        for(String key:keys){
            if(processed.containsKey(key)){
                continue;
            }

            //create pair
            Pair sp = new Pair();
            sp.vname = key;
            sp.psf = key;

            // put the new pair in queue
            queue.addLast(sp);

            // while queue is not empty keep on doing the work
            while(!queue.isEmpty()){
                // remove a pair from queue
                Pair rp = queue.removeFirst();
                if(processed.containsKey(rp.vname)){
                    return true;
                }

                // processed put
                processed.put(rp.vname,true);

                // de
                System.out.println(rp.vname+ " :: "+rp.psf);

                // nbrs
                Vertex rpvtx = vtces.get(rp.vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // loop on nbrs
                for(String nbr: nbrs){
                    // process only unprocessed nbrs
                    if(!processed.containsKey(nbr)){
                        // make a new pair of nbr and put in the queue
                        Pair np = new Pair();
                        np.vname = nbr;
                        np.psf = rp.vname+nbr;

                        queue.addLast(np);
                    }
                }
            }
        }
        return false;
    }

    //16. Is the graph is connected
    public boolean isConnected(){
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());
        int flag = 0;
        for(String key:keys){
            if(processed.containsKey(key)){
                continue;
            }

            flag++;
            //create pair
            Pair sp = new Pair();
            sp.vname = key;
            sp.psf = key;

            // put the new pair in queue
            queue.addLast(sp);

            // while queue is not empty keep on doing the work
            while(!queue.isEmpty()){
                // remove a pair from queue
                Pair rp = queue.removeFirst();
                if(processed.containsKey(rp.vname)){
                    continue;
                }

                // processed put
                processed.put(rp.vname,true);

                // de
                System.out.println(rp.vname+ " :: "+rp.psf);

                // nbrs
                Vertex rpvtx = vtces.get(rp.vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // loop on nbrs
                for(String nbr: nbrs){
                    // process only unprocessed nbrs
                    if(!processed.containsKey(nbr)){
                        // make a new pair of nbr and put in the queue
                        Pair np = new Pair();
                        np.vname = nbr;
                        np.psf = rp.vname+nbr;

                        queue.addLast(np);
                    }
                }
            }
        }
        if(flag >=2){
            return true;
        }
        else{
            return false;
        }
    }

    //17. Is the graph a tree
    public boolean isTree(){
        return !isCyclic() && isConnected();
    }

    //18. get connected nodes
    public ArrayList<ArrayList<String>> getConnected(){

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        for(String key:keys){
            if(processed.containsKey(key)){
                continue;
            }

            ArrayList<String> subans = new ArrayList<>();
            //create pair
            Pair sp = new Pair();
            sp.vname = key;
            sp.psf = key;

            // put the new pair in queue
            queue.addLast(sp);

            // while queue is not empty keep on doing the work
            while(!queue.isEmpty()){

                // remove a pair from queue
                Pair rp = queue.removeFirst();

                if(processed.containsKey(rp.vname)){
                    continue;
                }

                // proccessed put
                processed.put(rp.vname, true);

                // sys and put it in subans
                System.out.println(rp.vname+ " :: "+rp.psf);
                subans.add(rp.vname);

                // nbrs
                Vertex rpvtx = vtces.get(rp.vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // loop on nbrs
                for(String nbr: nbrs){

                    // process only unprocessed nbrs
                    if(!processed.containsKey(nbr)){
                        // make a new pair of nbr and put in the queue
                        Pair np = new Pair();
                        np.vname = nbr;
                        np.psf = rp.vname+nbr;

                        queue.addLast(np);
                    }
                }
            }

            // put  in final ans
            ans.add(subans);
        }
        return ans;
    }

    //19. Prims Algorithm
    private class PrimsPair implements Comparable<PrimsPair>{
        String vname;
        String acqvname;
        int cost;

        @Override
        public int compareTo(PrimsPair o) {
            return o.cost - this.cost;
        }
    }

    public Graph prims(){
        Graph mst = new Graph();
        HashMap<String,PrimsPair> map = new HashMap<>();

        HeapGeneric<PrimsPair> heap = new HeapGeneric<>();

        for(String key: vtces.keySet()){

            PrimsPair np = new PrimsPair();
            np.vname = key;
            np.acqvname = null;
            np.cost = Integer.MAX_VALUE;

            heap.add(np);
            map.put(key,np);

        }

        //till the heap is not empty keep on removing the pairs
        while(!heap.isEmpty()){

            //remove the pair
            PrimsPair rp = heap.remove();
            map.remove(rp.vname);

            // add to mst
            if(rp.acqvname == null){
                mst.addVertex(rp.vname);
                mst.addEdge(rp.vname, rp.acqvname, rp.cost);
            }

            //nbrs
            for(String nbr: vtces.get(rp.vname).nbrs.keySet()){

                // work for nbrs which are in heap
                if(map.containsKey(nbr)){

                    // get the old cost and the new cost
                     int oldCost = map.get(nbr).cost;
                     int newCost = vtces.get(rp.vname).nbrs.get(nbr);

                     // update the pair only when the new cost is less than old cost
                     if(newCost < oldCost){
                        PrimsPair gp = map.get(nbr);
                        gp.acqvname = rp.vname;
                        gp.cost = newCost;

                        heap.updatePriority(gp);
                     }
                }
            }
        }



        return mst;
    }

    //20. DIJSKTRA Algorithm
    private class DijsktraPair implements Comparable<DijsktraPair>{
        String vname;
        String psf;
        int cost;

        @Override
        public int compareTo(DijsktraPair o) {
            return o.cost - this.cost;
        }
    }

    public HashMap<String, Integer> dijsktra(String src){

        HashMap<String, Integer> ans = new HashMap<>();
        HashMap<String, DijsktraPair> map = new HashMap<>();

        HeapGeneric<DijsktraPair> heap = new HeapGeneric<>();

        for(String key: vtces.keySet()){

            DijsktraPair np = new DijsktraPair();
            np.vname = key;
            np.psf = "";
            np.cost = Integer.MAX_VALUE;

            if(key.equals(src)){
                np.cost = 0;
                np.psf = key;
            }

            heap.add(np);
            map.put(key,np);

        }

        //till the heap is not empty keep on removing the pairs
        while(!heap.isEmpty()){

            //remove the pair
            DijsktraPair rp = heap.remove();
            map.remove(rp.vname);

            // add to ans
            ans.put(rp.vname, rp.cost);

            //nbrs
            for(String nbr: vtces.get(rp.vname).nbrs.keySet()){

                // work for nbrs which are in heap
                if(map.containsKey(nbr)){

                    // get the old cost and the new cost
                    int oldCost = map.get(nbr).cost;
                    int newCost = rp.cost + vtces.get(rp.vname).nbrs.get(nbr);

                    // update the pair only when the new cost is less than old cost
                    if(newCost < oldCost){
                        DijsktraPair gp = map.get(nbr);
                        gp.psf = rp.psf + nbr;
                        gp.cost = newCost;

                        heap.updatePriority(gp);
                    }
                }
            }
        }



        return ans;
    }

    public class DisjointSet {
        HashMap<String, Node> map = new HashMap<>();

        private class Node {
            String data;
            Node parent;
            int rank;
        }
        public void create(String value) {
            Node nn = new Node();
            nn.data = value;
            nn.parent = nn;
            nn.rank = 0;

            map.put(value, nn);
        }
        public void union (String value1, String value2) {
            Node n1 = map.get(value1);
            Node n2 = map.get(value2);

            Node re1 = find(n1);
            Node re2 = find(n2);

            if(re1.data.equals(re2.data)) {
                return ;
            } else {
                if(re1.rank == re2.rank) {
                    re2.parent = re1;
                    re1.rank = re1.rank + 1;
                } else if(re1.rank > re2.rank) {
                    re2.parent = re1;
                } else {
                    re1.parent = re2;
                }
            }
        }

        public String find (String value) {
            return find(map.get(value)).data;
        }

        private Node find(Node node) {
            if(node == node.parent) {
                return node;
            }
            Node rr = find(node.parent);
            node.parent = rr; // path compression
            return rr;
        }
    }

    public class EdgePair implements Comparable<EdgePair> {

        String v1;
        String v2;
        int cost;

        @Override
        public int compareTo(EdgePair o) {
            return this.cost - o.cost;
        }

        public String toString() {
            return v1+ "-"+ v2 +" : "+ cost;
        }
    }

    public ArrayList<EdgePair> getAllEdges() {
        ArrayList<EdgePair> edges = new ArrayList<>();

        for(String vname: vtces.keySet()) {
            Vertex vtx = vtces.get(vname) ;
            for(String nbr : vtx.nbrs.keySet()) {
                EdgePair ep = new EdgePair();
                ep.v1 = vname;
                ep.v2 = nbr;
                ep.cost = vtx.nbrs.get(nbr);

                edges.add(ep);
            }
        }
        return edges;
    }

    public void kruskal() {

        // Sort the edges in the increasing order if weight
        ArrayList<EdgePair> edges = getAllEdges();
        Collections.sort(edges);
        //System.out.println(edges);

        DisjointSet set = new DisjointSet();

        // create vertices no. of sets
        for(String vname: vtces.keySet()) {
            set.create(vname);
        }

        // traverse over the edges
        for(EdgePair edge: edges) {
            String re1 = set.find(edge.v1);
            String re2 = set.find(edge.v2);

            // re same : ignore
            if(re1.equals(re2)) {
                continue;
            } else {
                System.out.println(edge);
                set.union(edge.v1, edge.v2);
            }
        }
    }
}
