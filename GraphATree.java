package com.graphs;

import java.util.*;

public class GraphATree {

    public static boolean is_it_a_tree(int node_count, List<Integer> edge_start, List<Integer> edge_end) {

        if(node_count < 2)
            return true;
        List<List<Integer>> edges = new ArrayList<>();
        List<Integer> edge = new ArrayList<>();
        //Mapping edges
        for(int i=0; i<edge_start.size() && i<edge_end.size(); i++){
            edge.add(edge_start.get(i));
            edge.add(edge_end.get(i));

            if(edge.size()==2){
                edges.add(new ArrayList<>(edge));
                edge.removeAll(edge);
            }
        }

        System.out.println(Arrays.toString(edges.toArray()));
        List<Integer> parent = new ArrayList<>();

        for(int i=0; i<node_count; i++){
            parent.add(i, i);
        }

        for(List<Integer> list: edges){
            int x = find(parent, list.get(0));
            int y = find(parent, list.get(1));

            if(x==y) {
                return false;
            }
            parent.set(x, y);
        }

        return edges.size() == node_count-1;
    }


    static int find(List<Integer> parent, int i){
        int x = parent.get(i);
        if(x != i){
            x = find(parent, parent.get(i));
        }

        return x;
    }
	
    public static boolean is_it_a_trees(int node_count, List<Integer> edge_start, List<Integer> edge_end) {

        // if(node_count < 2)
        // return true;


        List<List<Integer>> edges = new ArrayList<>();
        List<Integer> edge = new ArrayList<>();

        //Mapping edges
        for(int i=0; i<edge_start.size() && i<edge_end.size(); i++){

            edge.add(edge_start.get(i));
            edge.add(edge_end.get(i));

            if(edge.size()==2){
                edges.add(new ArrayList<>(edge));
                edge.removeAll(edge);
            }
        }



        List<List<Integer>> adjList = new ArrayList<>();

        for(int i=0; i<node_count; i++){
            adjList.add(new ArrayList<>());
        }


        for(List<Integer> e: edges){

            adjList.get(e.get(0)).add(e.get(1));
            adjList.get(e.get(1)).add(e.get(0));
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);

        while(!q.isEmpty()){

            int curr = q.poll();

            for(int neighbour: adjList.get(curr)){
                if(map.get(curr)==neighbour){
                    continue;
                }

                if(map.containsKey(neighbour)){
                    return false;
                }

                q.offer(neighbour);
                map.put(neighbour, curr);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Integer[] edgeA = {0,0,1,2};
        Integer[] edgeB= {3,1,2,0};

        List<Integer> list = Arrays.asList(edgeA);
        List<Integer> list2 = Arrays.asList(edgeB);

        System.out.println(GraphATree.is_it_a_trees(4, list, list2));
    }

}
