/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.queue;

import br.gov.frameworkdemoiselle.serpro.audit.domain.Trilha;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author 70744416353
 */
public class QueueTrilha {

    private static final LinkedBlockingQueue<Trilha> lbq;

    static{
         lbq = new LinkedBlockingQueue<Trilha>(7);
    }

    /**
     *
     * @param trilha
     * @return
     */
    public static boolean offer(Trilha trilha) {
        return lbq.offer(trilha);
    }

    /**
     *
     * @return
     */
    public static Trilha poll() {
        return lbq.poll();
    }

    /**
     *
     * @param trilha
     * @return
     */
    public static boolean contains(Trilha trilha) {
        return lbq.contains(trilha);
    }

    /**
     *
     * @return
     */
    public static boolean isEmpty() {
        return lbq.isEmpty();
    }

    /**
     *
     * @return
     */
    public static int remainingCapacity() {
        return lbq.remainingCapacity();
    }

    /**
     *
     * @return
     */
    public static int size() {
        return lbq.size();
    }

    private QueueTrilha() {
    }
}
