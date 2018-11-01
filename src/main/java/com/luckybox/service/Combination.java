package com.luckybox.service;

public class Combination {
	private int value;
	private Integer[] entrada;
	private int max;
	private int items;

	public Combination(Integer[] entrada, int r) {
		this.value = r;
		this.entrada = entrada;
		this.max = ~(1 << entrada.length);
		this.items = 1;
	}

	public boolean hasNext() {
		if (value != 0) {
			while (((this.items & this.max) != 0) && (countbits() != value))
				items += 1;
		}

		return (this.items & this.max) != 0;
	}

	private int countbits() {
		int i;
		int c;

		i = 1;
		c = 0;
		while ((this.max & i) != 0) {
			if ((this.items & i) != 0) {
				c++;
			}
			i = i << 1;
		}

		return c;
	}

	public int getSaidaLength() {
		if (value != 0) {
			return value;
		}

		return this.countbits();
	}

	public Integer[] next() {
		int exitIndex;
		int enterIndex; 
		int i;

		Integer[] saida = new Integer[this.getSaidaLength()];

		enterIndex = 0;
		exitIndex = 0;
		i = 1;

		while ((this.max & i) != 0) {
			if ((this.items & i) != 0) {
				saida[exitIndex] = entrada[enterIndex];
				exitIndex += 1;
			}
			enterIndex += 1;
			i = i << 1;
		}

		items += 1;

		return saida;
	}
}