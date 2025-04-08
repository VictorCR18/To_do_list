<template>
  <div class="pa-6">
    <!-- Formulário para adicionar nova tarefa -->
    <v-form
      @submit.prevent="adicionarTarefa"
      class="d-flex align-center mb-4 ga-4"
    >
      <v-text-field
        v-model="novaTarefa"
        placeholder="Digite uma tarefa"
        variant="outlined"
        hide-details
        class="flex-grow-1"
      />
      <v-btn type="submit" color="primary">Adicionar</v-btn>
    </v-form>

    <!-- Lista de tarefas -->
    <v-list>
      <v-list-item v-for="tarefa in tarefas" :key="tarefa.id" class="mb-2">
        <v-list-item-content>
          <!-- Input de edição -->
          <div
            v-if="tarefa.editando"
            class="d-flex align-center justify-space-between ga-2"
          >
            <v-text-field
              v-model="tarefa.titulo"
              @keyup.enter="salvarEdicao(tarefa)"
              @blur="salvarEdicao(tarefa)"
              hide-details
              dense
              class="flex-grow-1"
            />
            <v-btn icon @click="salvarEdicao(tarefa)">
              <v-icon size="20" color="green">mdi-check</v-icon>
            </v-btn>
          </div>

          <!-- Exibição normal -->
          <div v-else class="d-flex align-center justify-space-between ga-2">
            <v-checkbox
              v-model="tarefa.concluida"
              :label="tarefa.titulo"
              @change="atualizarTarefa(tarefa)"
              class="flex-grow-1"
              hide-details
              density="compact"
            />

            <!-- Botões ao lado -->
            <v-btn icon size="small" @click="editarTarefa(tarefa)">
              <v-icon size="20" color="blue">mdi-pencil</v-icon>
            </v-btn>
            <v-btn icon size="small" @click="deletarTarefa(tarefa)">
              <v-icon size="20" color="red">mdi-delete</v-icon>
            </v-btn>
          </div>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const tarefas = ref([]);
const novaTarefa = ref("");

const carregarTarefas = async () => {
  const { data } = await axios.get("http://localhost:8080/tarefas");
  tarefas.value = data.map((t) => ({ ...t, editando: false }));
};

const adicionarTarefa = async () => {
  if (!novaTarefa.value) return;

  const { data } = await axios.post("http://localhost:8080/tarefas", {
    titulo: novaTarefa.value,
    concluida: false,
  });

  tarefas.value.push({ ...data, editando: false });
  novaTarefa.value = "";
};

const atualizarTarefa = async (tarefa) => {
  await axios.put(`http://localhost:8080/tarefas/${tarefa.id}`, tarefa);
};

const deletarTarefa = async (tarefa) => {
  /*   const confirmar = confirm(`Tem certeza que deseja deletar "${tarefa.titulo}"?`);
  if (!confirmar) return; */

  await axios.delete(`http://localhost:8080/tarefas/${tarefa.id}`);
  tarefas.value = tarefas.value.filter((t) => t.id !== tarefa.id);
};

const editarTarefa = (tarefa) => {
  tarefa.editando = true;
};

const salvarEdicao = async (tarefa) => {
  tarefa.editando = false;
  await atualizarTarefa(tarefa);
};

onMounted(carregarTarefas);
</script>
