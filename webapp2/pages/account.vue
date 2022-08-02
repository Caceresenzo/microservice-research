<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title> Account </v-card-title>
          <v-card-text>
            <pre>{{ user }}</pre>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12">
        <v-card :loading="fetchState.pending">
          <v-card-title>
            API Keys
            <v-spacer />
            <v-btn icon :loading="fetchState.pending" @click="fetch">
              <v-icon>mdi-refresh</v-icon>
            </v-btn>
          </v-card-title>
          <v-card-text>
            <pre>{{ fetchState }}</pre>
          </v-card-text>
          <v-list>
            <v-list-item v-for="apiKey in apiKeys" :key="apiKey.id">
              <v-list-item-icon>
                <v-icon>mdi-key</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title> ID: {{ apiKey.id }} </v-list-item-title>
                <v-list-item-subtitle>
                  <v-chip v-for="scope in apiKey.scopes" :key="scope" small>
                    {{ scope }}
                  </v-chip>
                </v-list-item-subtitle>
              </v-list-item-content>
              <v-list-item-action-text>
                <v-text-field
                  class="d-inline-block"
                  dense
                  hide-details
                  readonly
                  outlined
                  :value="apiKey.truncated"
                />
              </v-list-item-action-text>
              <v-list-item-action>
                <v-btn icon>
                  <v-icon>mdi-dots-vertical</v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {
  defineComponent,
  ref,
  useContext,
  useFetch,
} from '@nuxtjs/composition-api'

export default defineComponent({
  setup() {
    const { $axios, $auth } = useContext()

    const { user } = $auth

    const apiKeys = ref([])

    const { fetch, fetchState } = useFetch(async () => {
      apiKeys.value = (await $axios.$get('/v1/api-keys')).content
    })

    return { user, apiKeys, fetch, fetchState }
  },
})
</script>
