package com.springboot.client.SpringClient.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

@Component
public class IndexServcie
{
	// Lucene索引文件路径
	static String dir = "E:\\lucence";
	// 定义分词器
	static Analyzer analyzer = new IKAnalyzer();

	static Directory directory;
	
	static IndexWriter indexWriter;
	
	/**
	 * 封裝一个方法，用于将数据库中的数据解析为一个个关键字词存储到索引文件中
	 * 
	 * @param doc
	 */
	public void write(Document doc)
	{
		try
		{
			// 索引库的存储目录
			directory = FSDirectory.open(new File(dir).toPath());
			// 关联当前lucence版本和分值器
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			// 传入目录和分词器
			indexWriter = new IndexWriter(directory, config);
			// 写入到目录文件中
			indexWriter.addDocument(doc);
			// 提交事务
			indexWriter.commit();
			// 关闭流
			indexWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// 搜索
	public List<Map<String,Object>> search(String field, String value) throws Exception
	{

		// 索引库的存储目录
		Directory directory = FSDirectory.open(new File(dir).toPath());
		// 读取索引库的存储目录
		DirectoryReader ireader = DirectoryReader.open(directory);
		// 搜索类
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// lucence查询解析器，用于指定查询的属性名和分词器
		QueryParser parser = new QueryParser(Version.LATEST, field, analyzer);
		// 搜索
		Query query = parser.parse(value);
		// 最终被分词后添加的前缀和后缀处理器，默认是粗体<B></B>
		SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<font color=red>", "</font>");
		// 高亮搜索的词添加到高亮处理器中
		Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));

		// 获取搜索的结果，指定返回document返回的个数
		ScoreDoc[] hits = isearcher.search(query, 5).scoreDocs;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// 遍历，输出
		for (int i = 0; i < hits.length; i++)
		{
			int id = hits[i].doc;
			Document hitDoc = isearcher.doc(hits[i].doc);
			Map<String, Object> map = new HashMap<>();
			map.put("foodid", hitDoc.get("foodid"));

			// 获取到foodname
			String foodname = hitDoc.get("foodname");
			// 将查询的词和搜索词匹配，匹配到添加前缀和后缀
			TokenStream tokenStream = TokenSources.getAnyTokenStream(isearcher.getIndexReader(), id, "foodname",
					analyzer);
			// 传入的第二个参数是查询的值
			TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, foodname, false, 10);
			String foodValue = "";
			for (int j = 0; j < frag.length; j++)
			{
				if ((frag[j] != null) && (frag[j].getScore() > 0))
				{
					// 获取 foodname 的值
					foodValue = ((frag[j].toString()));
				}
			}
			map.put("foodname", foodValue);

			map.put("price", hitDoc.get("price"));
			map.put("imagepath", hitDoc.get("imagepath"));
			list.add(map);
		}
		ireader.close();
		directory.close();
		return list;
	}

	/**
	 * 增加索引
	 * 
	 * @throws Exception
	 */
	public static void insert() throws Exception
	{
		String text5 = "hello,goodbye,man,woman";
		Date date1 = new Date();
		analyzer = new StandardAnalyzer(Version.LATEST);
		directory = FSDirectory.open(new File(dir).toPath());

		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		indexWriter = new IndexWriter(directory, config);

		Document doc1 = new Document();
		doc1.add(new TextField("filename", "text5", Store.YES));
		doc1.add(new TextField("content", text5, Store.YES));
		indexWriter.addDocument(doc1);

		indexWriter.commit();
		indexWriter.close();

		Date date2 = new Date();
		System.out.println("增加索引耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
	}

	/**
	 * 删除索引
	 * 
	 * @param str
	 *            删除的关键字
	 * @throws Exception
	 */
	public static void delete(String str) throws Exception
	{
		Date date1 = new Date();
		analyzer = new StandardAnalyzer(Version.LATEST);
		directory = FSDirectory.open(new File(dir).toPath());

		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		indexWriter = new IndexWriter(directory, config);

		indexWriter.deleteDocuments(new Term("filename", str));

		indexWriter.close();

		Date date2 = new Date();
		System.out.println("删除索引耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
	}
	/**
     * 更新索引
     * 
     * @throws Exception
     */
    public static void update() throws Exception {
        String text1 = "update,hello,man!";
        Date date1 = new Date();
         analyzer = new StandardAnalyzer(Version.LATEST);
         directory = FSDirectory.open(new File(dir).toPath());
 
         IndexWriterConfig config = new IndexWriterConfig(analyzer);
         indexWriter = new IndexWriter(directory, config);
         
         Document doc1 = new Document();
        doc1.add(new TextField("filename", "text1", Store.YES));
        doc1.add(new TextField("content", text1, Store.YES));
        
        indexWriter.updateDocument(new Term("filename","text1"), doc1);
        
         indexWriter.close();
         
         Date date2 = new Date();
         System.out.println("更新索引耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
    }
    /**
     * 关键字查询
     * 
     * @param str
     * @throws Exception
     */
    public static void search(String str) throws Exception {
        directory = FSDirectory.open(new File(dir).toPath());
        analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
 
        QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "content",analyzer);
        Query query = parser.parse(str);
 
        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("filename"));
            System.out.println(hitDoc.get("content"));
        }
        ireader.close();
        directory.close();
    }

}
