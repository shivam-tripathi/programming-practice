
export enum DataType {
	int = 'integer',
}

export enum IndexType {
	unique = 'unique',
}

interface Field {
	name: string;
	type: DataType;
	index: IndexType | null;
	ref: Field[]; // foreign key
}

interface Schema {
	fields: Field[];
}

interface Query {

}

export interface Table {
	name: string;
	schema: Schema;
	query<T>(q: Query): T[];
}

export interface Database {
	tables: Record<string, Table>;
	createTable(table: Table): void;
	getTable(): Table;
}

export class MemTable implements Table { }
