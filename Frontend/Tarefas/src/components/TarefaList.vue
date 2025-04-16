<template>
  <div class="pa-6">
    <v-form
      @submit.prevent="adicionarTarefa"
      class="d-flex align-center mb-4 ga-4"
    >
      <v-text-field
        v-model="novaTarefa"
        placeholder="Digite uma tarefa"
        maxlength="100"
        variant="outlined"
        hide-details
        class="flex-grow-1"
      />
      <v-btn type="submit" color="primary">Adicionar</v-btn>
    </v-form>

    <!-- Lista de tarefas -->
    <v-list>
      <v-list-item
        v-for="tarefa in tarefas"
        :key="tarefa.id"
        class="mb-2 tarefa-item"
      >
        <v-list-item-content>
          <!-- Input de edição -->
          <div
            v-if="tarefa.editando"
            class="d-flex align-center justify-space-between ga-2 py-1"
          >
            <v-text-field
              v-model="tarefa.titulo"
              @keyup.enter="salvarEdicao(tarefa)"
              @blur="salvarEdicao(tarefa)"
              maxlength="100"
              variant="solo-filled"
              hide-details
              dense
              class="flex-grow-1"
            />
            <v-btn icon @click="salvarEdicao(tarefa)">
              <v-icon size="20" color="green">mdi-check</v-icon>
            </v-btn>
          </div>

          <!-- Exibição normal -->
          <div
            v-else
            class="d-flex align-center justify-space-between ga-2 py-1"
          >
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
            <v-btn icon size="small" @click="confirmarExclusao(tarefa)">
              <v-icon size="20" color="red">mdi-delete</v-icon>
            </v-btn>
          </div>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
  <!-- Modal de confirmação -->
  <ExcluirTarefaModal
    v-model="mostrarModal"
    @confirmar="deletarTarefaConfirmado"
  />
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import ExcluirTarefaModal from "./ExcluirTarefaModal.vue";
import TarefaService from "../service/tarefaService";
import type { Tarefa } from "../types/tarefas";

const tarefas = ref<Tarefa[]>([]);
const novaTarefa = ref("");

const mostrarModal = ref(false);
const tarefaParaExcluir = ref<Tarefa | null>(null);

const confirmarExclusao = (tarefa: Tarefa) => {
  tarefaParaExcluir.value = tarefa;
  mostrarModal.value = true;
};

const carregarTarefas = async () => {
  tarefas.value = await TarefaService.listar();
};

const adicionarTarefa = async () => {
  const tituloLimpo = novaTarefa.value.trim();
  if (!tituloLimpo) return;

  if (!novaTarefa.value) return;

  const nova = await TarefaService.criar(novaTarefa.value);
  tarefas.value.push(nova);
  novaTarefa.value = "";
};

const atualizarTarefa = async (tarefa: Tarefa) => {
  await TarefaService.atualizar(tarefa);
};

// const deletarTarefa = async (tarefa: Tarefa) => {
//   await TarefaService.deletar(tarefa.id);
//   tarefas.value = tarefas.value.filter((t) => t.id !== tarefa.id);
// };

const deletarTarefaConfirmado = async () => {
  if (tarefaParaExcluir.value) {
    await TarefaService.deletar(tarefaParaExcluir.value.id);
    tarefas.value = tarefas.value.filter(
      (t) => t.id !== tarefaParaExcluir.value?.id
    );
    tarefaParaExcluir.value = null;
  }
};

const editarTarefa = (tarefa: Tarefa) => {
  tarefa.editando = true;
};

const salvarEdicao = async (tarefa: Tarefa) => {
  tarefa.editando = false;
  await atualizarTarefa(tarefa);
};

onMounted(carregarTarefas);
</script>
