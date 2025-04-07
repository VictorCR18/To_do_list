<template>
  <div>
    <form @submit.prevent="adicionarTarefa">
      <input v-model="novaTarefa" placeholder="Digite uma tarefa" />
      <button type="submit">Adicionar</button>
    </form>

    <ul>
      <li v-for="tarefa in tarefas" :key="tarefa.id">
        <label>
          <input
            type="checkbox"
            v-model="tarefa.concluida"
            @change="atualizarTarefa(tarefa)"
          />
          <span
            :style="{
              textDecoration: tarefa.concluida ? 'line-through' : 'none',
            }"
          >
            {{ tarefa.titulo }}
          </span>
        </label>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const tarefas = ref([]);
const novaTarefa = ref("");

const carregarTarefas = async () => {
  const { data } = await axios.get("http://localhost:8080/tarefas");
  tarefas.value = data;
};

const adicionarTarefa = async () => {
  if (!novaTarefa.value) return;

  const { data } = await axios.post("http://localhost:8080/tarefas", {
    titulo: novaTarefa.value,
    concluida: false,
  });

  tarefas.value.push(data);
  novaTarefa.value = "";
};

const atualizarTarefa = async (tarefa) => {
  await axios.put(`http://localhost:8080/tarefas/${tarefa.id}`, tarefa);
};

onMounted(carregarTarefas);
</script>
