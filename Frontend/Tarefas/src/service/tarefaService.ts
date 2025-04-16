import axios from "axios";
import type { Tarefa } from "../types/tarefas";

const API_URL = "http://localhost:8080/tarefas";

export default {
  async listar(): Promise<Tarefa[]> {
    const { data } = await axios.get<Tarefa[]>(API_URL);
    return data.map((t) => ({ ...t, editando: false }));
  },

  async criar(titulo: string): Promise<Tarefa> {
    const { data } = await axios.post<Tarefa>(API_URL, {
      titulo,
      concluida: false,
    });
    return { ...data, editando: false };
  },

  async atualizar(tarefa: Tarefa): Promise<void> {
    await axios.put(`${API_URL}/${tarefa.id}`, tarefa);
  },

  async deletar(id: number): Promise<void> {
    await axios.delete(`${API_URL}/${id}`);
  },
};
